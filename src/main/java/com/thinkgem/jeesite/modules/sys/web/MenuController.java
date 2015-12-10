/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.sys.web;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.collections.CollectionUtils;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.modules.sys.entity.Menu;
import com.thinkgem.jeesite.modules.sys.service.SystemService;
import com.thinkgem.jeesite.modules.sys.utils.UserUtils;

/**
 * 菜单Controller
 * @author ThinkGem
 * @version 2013-3-23
 */
@Controller
@RequestMapping(value = "${adminPath}/sys/menu")
public class MenuController extends BaseController {

	@Autowired
	private SystemService systemService;
	
	@ModelAttribute("menu")
	public Menu get(@RequestParam(required=false) String id) {
		if (StringUtils.isNotBlank(id)){
			return systemService.getMenu(id);
		}else{
			return new Menu();
		}
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = {"list", ""})
	public String list(Model model) {
		List<Menu> list = Lists.newArrayList();
		List<Menu> sourcelist = systemService.findAllMenu();
		Menu.sortList(list, sourcelist, Menu.getRootId(), true);
        model.addAttribute("list", list);
		return "modules/sys/menuList";
	}

	@RequiresPermissions("sys:menu:view")
	@RequestMapping(value = "form")
	public String form(Menu menu, Model model) {
		if (menu.getParent()==null||menu.getParent().getId()==null){
			menu.setParent(new Menu(Menu.getRootId()));
		}
		menu.setParent(systemService.getMenu(menu.getParent().getId()));
		// 获取排序号，最末节点排序号+30
		if (StringUtils.isBlank(menu.getId())){
			List<Menu> list = Lists.newArrayList();
			List<Menu> sourcelist = systemService.findAllMenu();
			Menu.sortList(list, sourcelist, menu.getParentId(), false);
			if (list.size() > 0){
				menu.setSort(list.get(list.size()-1).getSort() + 30);
			}
		}
		model.addAttribute("menu", menu);
		return "modules/sys/menuForm";
	}
	
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "save")
	public String save(Menu menu, Model model, RedirectAttributes redirectAttributes) {
		if(!UserUtils.getUser().isAdmin()){
			addMessage(redirectAttributes, "越权操作，只有超级管理员才能添加或修改数据！");
			return "redirect:" + adminPath + "/sys/role/?repage";
		}
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
		if (!beanValidator(model, menu)){
			return form(menu, model);
		}
		systemService.saveMenu(menu);
		addMessage(redirectAttributes, "保存菜单'" + menu.getName() + "'成功");
		return "redirect:" + adminPath + "/sys/menu/";
	}
	
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "delete")
	public String delete(Menu menu, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
//		if (Menu.isRoot(id)){
//			addMessage(redirectAttributes, "删除菜单失败, 不允许删除顶级菜单或编号为空");
//		}else{
			systemService.deleteMenu(menu);
			addMessage(redirectAttributes, "删除菜单成功");
//		}
		return "redirect:" + adminPath + "/sys/menu/";
	}

	@RequiresPermissions("user")
	@RequestMapping(value = "tree")
	public String tree() {
		return "modules/sys/menuTree";
	}


	/**
	 * 直接返回MenuTree总是出现中文问号，导致左菜单显示不全
	 * @param parentId
	 * @return
	 */
	@RequiresPermissions("user")
	@RequestMapping(value = "treeMenu")
	@ResponseBody
	public String treeMenu(HttpServletRequest request, String parentId) {
		List<Menu> menuList = UserUtils.getMenuList();
		StringBuffer sb = new StringBuffer("<div class=\"accordion\" id=\"menu-" + parentId + "\">");
		if (!CollectionUtils.isEmpty(menuList)) {
			int i = 0;
			for (Menu menu : menuList) {
				if (menu.getIsShow().equals("1") && menu.getParentId().equals(parentId != null ? parentId : "1")) {
					sb.append("<div class=\"accordion-group\">")
						.append("    <div class=\"accordion-heading\">")
						.append("		<a class=\"accordion-toggle\" data-toggle=\"collapse\" data-parent=\"#menu-" + menu.getParentId() + "\" ")
						.append("			data-href=\"#collapse-" + menu.getId() + "\" href=\"#collapse-" + menu.getId() + "\" title=\"" + menu.getRemarks() + "\">" )
						.append(" 			<i class=\"icon-chevron-" + (i == 0 ? "down" : "right") + "\"></i>&nbsp;" + menu.getName() + "</a>")
						.append("	 </div>");

					sb.append("<div id=\"collapse-" + menu.getId() + "\" class=\"accordion-body collapse " + (i == 0 ? "in" :"") + "\"> ")
						.append("<div class=\"accordion-inner\">")
						.append("	<ul class=\"nav nav-list\">");

					for (Menu menu2 : menuList) {
						if (menu2.getIsShow().equals("1") && menu2.getParentId().equals(menu.getId())) {
							sb.append("<li><a data-href=\".menu3-" + menu2.getId() + "\" href=\"" + (menu2.getHref() != null && !menu2.getHref().contains("://") ? (request.getContextPath() + Global.getAdminPath()) : "" ) + (menu2.getHref() != null ? menu2.getHref() : "/404") + "\"" )
								.append(" target=\"" + (!StringUtils.isEmpty(menu2.getTarget()) ? menu2.getTarget() : "mainFrame") + "\"> ")
								.append("<i class=\"icon-" + (StringUtils.isNotBlank(menu2.getIcon()) ? menu2.getIcon() : "circle-arrow-right") + "\"> </i>&nbsp;" + menu2.getName() + "</a>")
								.append("<ul class=\"nav nav-list hide\" style=\"margin:0;padding-right:0;\">");

							for (Menu menu3 : menuList) {
								if (menu3.getIsShow().equals("1") && menu3.getParentId().equals(menu2.getId())) {
									sb.append("<li class=\"menu3-" + menu2.getId() + " hide\">")
										.append(" <a href=\"" + (menu3.getHref() != null && !menu3.getHref().contains("://") ? request.getContextPath() + Global.getAdminPath() : "") + (menu3.getHref() != null ? menu3.getHref() : "/404") + "\"")
										.append(" target=\"" + (!StringUtils.isEmpty(menu3.getTarget()) ? menu3.getTarget() : "mainFrame") + "\"> ")
										.append("<i class=\"icon-" + (StringUtils.isNotBlank(menu3.getIcon()) ? menu3.getIcon() : "circle-arrow-right") + "\"> </i>&nbsp;" + menu3.getName() + "</a>")
										.append("</li>");
								}
							}

							sb.append("</ul></li>");

						}
					}
					sb.append("</ul>").append("</div></div></div>");
				}
				i++;
			}
		}

		sb.append("</div>");

		return sb.toString();
	}


	@RequiresPermissions("user")
	@RequestMapping(value = "treeselect")
	public String treeselect(String parentId, Model model) {
		model.addAttribute("parentId", parentId);
		return "modules/sys/menuTreeselect";
	}
	
	/**
	 * 批量修改菜单排序
	 */
	@RequiresPermissions("sys:menu:edit")
	@RequestMapping(value = "updateSort")
	public String updateSort(String[] ids, Integer[] sorts, RedirectAttributes redirectAttributes) {
		if(Global.isDemoMode()){
			addMessage(redirectAttributes, "演示模式，不允许操作！");
			return "redirect:" + adminPath + "/sys/menu/";
		}
    	for (int i = 0; i < ids.length; i++) {
    		Menu menu = new Menu(ids[i]);
    		menu.setSort(sorts[i]);
    		systemService.updateMenuSort(menu);
    	}
    	addMessage(redirectAttributes, "保存菜单排序成功!");
		return "redirect:" + adminPath + "/sys/menu/";
	}
	
	/**
	 * isShowHide是否显示隐藏菜单
	 * @param extId
	 * @param isShowHidden
	 * @param response
	 * @return
	 */
	@RequiresPermissions("user")
	@ResponseBody
	@RequestMapping(value = "treeData")
	public List<Map<String, Object>> treeData(@RequestParam(required=false) String extId,@RequestParam(required=false) String isShowHide, HttpServletResponse response) {
		List<Map<String, Object>> mapList = Lists.newArrayList();
		List<Menu> list = systemService.findAllMenu();
		for (int i=0; i<list.size(); i++){
			Menu e = list.get(i);
			if (StringUtils.isBlank(extId) || (extId!=null && !extId.equals(e.getId()) && e.getParentIds().indexOf(","+extId+",")==-1)){
				if(isShowHide != null && isShowHide.equals("0") && e.getIsShow().equals("0")){
					continue;
				}
				Map<String, Object> map = Maps.newHashMap();
				map.put("id", e.getId());
				map.put("pId", e.getParentId());
				map.put("name", e.getName());
				mapList.add(map);
			}
		}
		return mapList;
	}
}
