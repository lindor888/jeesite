/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.dao;

import com.thinkgem.jeesite.common.persistence.CrudDao;
import com.thinkgem.jeesite.common.persistence.annotation.MyBatisDao;
import com.thinkgem.jeesite.modules.order.entity.SysOrder;
import com.thinkgem.jeesite.modules.order.entity.SysOrderSummary;

import java.util.List;

/**
 * 订单DAO接口
 * @author zhajing
 * @version 2015-09-17
 */
@MyBatisDao
public interface SysOrderDao extends CrudDao<SysOrder> {

    public List<SysOrderSummary> listSummary(SysOrder entity);

    public List<SysOrder> findListByIds(List idList);
}