/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.service;

import java.util.List;
import java.util.Map;

import com.thinkgem.jeesite.modules.order.entity.SysOrderSummary;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.thinkgem.jeesite.common.persistence.Page;
import com.thinkgem.jeesite.common.service.CrudService;
import com.thinkgem.jeesite.modules.order.entity.SysOrder;
import com.thinkgem.jeesite.modules.order.dao.SysOrderDao;

/**
 * 订单Service
 * @author zhajing
 * @version 2015-09-17
 */
@Service
@Transactional(readOnly = true)
public class SysOrderService extends CrudService<SysOrderDao, SysOrder> {

	public SysOrder get(String id) {
		return super.get(id);
	}
	
	public List<SysOrder> findList(SysOrder sysOrder) {
		return super.findList(sysOrder);
	}
	
	public Page<SysOrder> findPage(Page<SysOrder> page, SysOrder sysOrder) {
		return super.findPage(page, sysOrder);
	}
	
	@Transactional(readOnly = false)
	public void save(SysOrder sysOrder) {
		super.save(sysOrder);
	}
	
	@Transactional(readOnly = false)
	public void delete(SysOrder sysOrder) {
		super.delete(sysOrder);
	}

	public List<SysOrderSummary> listSummary(SysOrder entity) {
		return dao.listSummary(entity);
	}

	public List<SysOrder> findListByIds(List idList) {
		return dao.findListByIds(idList);
	}
	
}