/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.thinkgem.jeesite.common.mapper.JsonMapper;
import com.thinkgem.jeesite.common.utils.CalendarUtil;
import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.ExportExcel;
import com.thinkgem.jeesite.modules.order.entity.SysOrderSummary;
import com.thinkgem.jeesite.modules.sys.entity.Log;
import com.thinkgem.jeesite.modules.sys.entity.User;
import com.thinkgem.jeesite.modules.sys.utils.DictUtils;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.collections.ListUtils;
import org.apache.commons.lang3.ArrayUtils;
import org.apache.log4j.Logger;
import org.apache.shiro.authz.annotation.RequiresPermissions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.thinkgem.jeesite.common.config.Global;
import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.web.BaseController;
import com.thinkgem.jeesite.common.utils.StringUtils;
import com.thinkgem.jeesite.modules.order.entity.SysOrder;
import com.thinkgem.jeesite.modules.order.service.SysOrderService;

import java.math.BigDecimal;
import java.util.*;

/**
 * 订单Controller
 * @author zhajing
 * @version 2015-09-17
 */
@Controller
@RequestMapping(value = "${adminPath}/order/sysOrder")
public class SysOrderController extends BaseController {
	private Logger logger = Logger.getLogger(SysOrderController.class);
	//茶具
	private static final String PRO_TYPE_TEA = "tea";

	//未处理
	private static final String STATE_UNDO = "undo";
	//成交
	private static final String STATE_DEAL = "deal";
	//无效
	private static final String STATE_INVALID = "invalid";

	@Autowired
	private SysOrderService sysOrderService;

	@ModelAttribute
	public SysOrder get(@RequestParam(required = false) String id) {
		SysOrder entity = null;
		if (StringUtils.isNotBlank(id)) {
			entity = sysOrderService.get(id);
		}
		if (entity == null) {
			entity = new SysOrder();
		}
		return entity;
	}

	@RequiresPermissions("order:sysOrder:view")
	@RequestMapping(value = {"list", ""})
	public String list(SysOrder sysOrder, HttpServletRequest request, HttpServletResponse response, Model model) {

		if (StringUtils.isNoneEmpty(sysOrder.getSearchType())) {
			Date now = new Date();
			Date firstDate = null;
			Date lastDate = null;
			if (SysOrder.SEARCH_TYPE_YES.equalsIgnoreCase(sysOrder.getSearchType())) {
				now = DateUtils.getYestoday(now);
				firstDate = CalendarUtil.rollToFirstTimeOfDate(now);
				lastDate = CalendarUtil.rollToLastTimeOfDate(now);

			} else if (SysOrder.SEARCH_TYPE_TODAY.equalsIgnoreCase(sysOrder.getSearchType())) {
				firstDate = CalendarUtil.rollToFirstTimeOfDate(now);
				lastDate = CalendarUtil.rollToLastTimeOfDate(now);

			} else if (SysOrder.SEARCH_TYPE_WEEK.equalsIgnoreCase(sysOrder.getSearchType())) {
				Calendar calendar = Calendar.getInstance();
				firstDate = CalendarUtil.rollToFirstTimeOfDate(CalendarUtil.getFirstDayOfWeek(calendar));
				lastDate = CalendarUtil.rollToLastTimeOfDate(CalendarUtil.getLastDayOfWeek(calendar));

			} else if (SysOrder.SEARCH_TYPE_MONTH.equalsIgnoreCase(sysOrder.getSearchType())) {
				Calendar calendar = Calendar.getInstance();
				firstDate = CalendarUtil.rollToFirstTimeOfDate(CalendarUtil.getFirstDayOfMonth(calendar));
				lastDate = CalendarUtil.rollToLastTimeOfDate(CalendarUtil.getLastDayOfMonth(calendar));
			}
			sysOrder.setBeginOrderDate(firstDate);
			sysOrder.setEndOrderDate(lastDate);
		}

		List<SysOrderSummary> sysOrderSummaries = sysOrderService.listSummary(sysOrder);

		Integer totalCount = 0;
		BigDecimal totalAmount = new BigDecimal(0);
		if (!CollectionUtils.isEmpty(sysOrderSummaries)) {
			for (SysOrderSummary sos : sysOrderSummaries) {
				totalCount += sos.getStatusCount();
				totalAmount = totalAmount.add(sos.getStatusAmout());
			}
		}
		model.addAttribute("totalCount", totalCount);
		model.addAttribute("totalAmount", totalAmount);

		Page<SysOrder> page = sysOrderService.findPage(new Page<SysOrder>(request, response), sysOrder);

		model.addAttribute("page", page);
		model.addAttribute("orderSummary", sysOrderSummaries);
		model.addAttribute("ordStateSelect", DictUtils.getDictList("ordState"));
		return "modules/order/sysOrderList";
	}

	@RequiresPermissions("order:sysOrder:view")
	@RequestMapping(value = "form")
	public String form(SysOrder sysOrder, Model model) {
		model.addAttribute("sysOrder", sysOrder);
		return "modules/order/sysOrderForm";
	}

	@RequiresPermissions("order:sysOrder:edit")
	@RequestMapping(value = "edit")
	public String edit(SysOrder sysOrder, Model model) {
		model.addAttribute("sysOrder", sysOrder);
		return "modules/order/sysOrderFormView";
	}

	@RequiresPermissions("order:sysOrder:edit")
	@RequestMapping(value = "changeStatus")
	public String changeStatus(SysOrder sysOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysOrder)) {
			return edit(sysOrder, model);
		}
		SysOrder sysOrder1 = get(sysOrder.getId());
		sysOrder1.setStatus(sysOrder.getStatus());
		sysOrder1.setOperremarks(sysOrder.getOperremarks());
		sysOrderService.save(sysOrder1);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:" + Global.getAdminPath() + "/order/sysOrder/?repage";
	}

	@RequiresPermissions("order:sysOrder:edit")
	@RequestMapping(value = "save")
	public String save(SysOrder sysOrder, Model model, RedirectAttributes redirectAttributes) {
		if (!beanValidator(model, sysOrder)) {
			return form(sysOrder, model);
		}
		sysOrderService.save(sysOrder);
		addMessage(redirectAttributes, "保存订单成功");
		return "redirect:" + Global.getAdminPath() + "/order/sysOrder/?repage";
	}

	@RequiresPermissions("order:sysOrder:edit")
	@RequestMapping(value = "delete")
	public String delete(SysOrder sysOrder, RedirectAttributes redirectAttributes) {
		sysOrderService.delete(sysOrder);
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:" + Global.getAdminPath() + "/order/sysOrder/?repage";
	}

	@RequiresPermissions("order:sysOrder:edit")
	@RequestMapping(value = "batchDelete")
	public String batchDelete(SysOrder sysOrder, HttpServletRequest request, RedirectAttributes redirectAttributes) {

		String selectedValue = request.getParameter("selectedValue");
		List<SysOrder> result = null;
		if (!StringUtils.isEmpty(selectedValue)) {
			String[] ids = StringUtils.split(selectedValue, ",");
			List idList = new ArrayList();
			for (String id : ids) {
				idList.add(id);
			}
			result = sysOrderService.findListByIds(idList);
			if (!CollectionUtils.isEmpty(result)) {
				for (SysOrder order : result) {
					sysOrderService.delete(order);
				}
			}
		}
		addMessage(redirectAttributes, "删除订单成功");
		return "redirect:" + Global.getAdminPath() + "/order/sysOrder/?repage";
	}

	/**
	 * 导出订单数据
	 *
	 * @param sysOrder
	 * @param request
	 * @param response
	 * @param redirectAttributes
	 * @return
	 */
	@RequiresPermissions("order:sysOrder:view")
	@RequestMapping(value = "export", method = RequestMethod.POST)
	public String exportFile(SysOrder sysOrder, HttpServletRequest request, HttpServletResponse response, RedirectAttributes redirectAttributes) {
		try {
			String fileName = "订单" + DateUtils.getDate("yyyyMMddHHmmss") + ".xlsx";
			String selectedValue = request.getParameter("selectedValue");
			List<SysOrder> result = null;
			if (!StringUtils.isEmpty(selectedValue)) {
				String[] ids = StringUtils.split(selectedValue, ",");
				List idList = new ArrayList();
				for (String id : ids) {
					idList.add(id);
				}
				result = sysOrderService.findListByIds(idList);
			} else {
				Page<SysOrder> page = sysOrderService.findPage(new Page<SysOrder>(request, response), sysOrder);
				result = page.getList();
			}
			new ExportExcel("", SysOrder.class).setDataList(result).write(response, fileName).dispose();
			return null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(redirectAttributes, "导出订单失败！失败信息：" + e.getMessage());
		}
		return "redirect:" + adminPath + "/order/sysOrder/list?repage";
	}

	/**
	 * 对外下单接口
	 * @param request
	 * @param response
	 * @return
	 */
	@RequestMapping(value = "openOrder", method={RequestMethod.POST, RequestMethod.GET})
	public String openOrder(HttpServletRequest request, HttpServletResponse response) {

		Map result = new HashMap();
		SysOrder sysOrder = new SysOrder();
		String cid = request.getParameter("cid");
		String product = request.getParameter("wfproduct");
		Double amount = StringUtils.toDouble(request.getParameter("wfprice"));
		String customerName = request.getParameter("wfname");
		String customerPhone = request.getParameter("wfmob");
		String customerAddress = request.getParameter("wfaddress");
		String remarks = request.getParameter("wfguest");
		String linkUrl = request.getParameter("WFDDURL");
		String wfrnmb = request.getParameter("wfIP"); //下单IP
		String wfSource = request.getParameter("wfSource");  //下单来路
		String productType = request.getParameter("productType");  //产品类型
		if (StringUtils.isEmpty(productType)) {
			productType = PRO_TYPE_TEA;
		}


//		String callback = request.getParameter("callback");

		if (StringUtils.isEmpty(customerPhone)) {
			result.put("success", "false");
			result.put("mes", "客户电话不能为空");
			request.setAttribute("error", "客户电话不能为空");
			return "modules/order/orderfail";
			//return callback + "(" + JsonMapper.toJsonString(result) + ")";
		}

		if (StringUtils.isEmpty(product)) {
			result.put("success", "false");
			result.put("mes", "选购的产品不能为空");
			request.setAttribute("error", "选购的产品不能为空");
			return "modules/order/orderfail";
			//return callback + "(" + JsonMapper.toJsonString(result) + ")";
		}

		sysOrder.setCid(cid);
		sysOrder.setProduct(product);
		sysOrder.setCustomerAddress(customerAddress);
		sysOrder.setCustomerName(customerName);
		sysOrder.setCustomerPhone(customerPhone);
		sysOrder.setAmount(amount);
		sysOrder.setLinkAddr(linkUrl);
		sysOrder.setRemarks(remarks);
		sysOrder.setOrderDate(new Date());
		sysOrder.setProducttype(productType);
		sysOrder.setStatus(STATE_UNDO);
		sysOrder.setDelFlag("0");
		sysOrder.setOrderIP(wfrnmb);
		sysOrder.setOrderSource(wfSource);

		try {
			sysOrderService.save(sysOrder);
			result.put("success", "true");
			result.put("mes", "下单成功");

		} catch (Exception e) {

			result.put("success", "false");
			result.put("mes", e.getMessage());
			logger.error("下单失败:\\n" + e.getMessage());
			e.printStackTrace();

			request.setAttribute("error", "下单失败。");
			return "modules/order/orderfail";
		}

		//return callback + "(" + JsonMapper.toJsonString(result) + ")";

		return "modules/order/ordersuccess";
	}
}