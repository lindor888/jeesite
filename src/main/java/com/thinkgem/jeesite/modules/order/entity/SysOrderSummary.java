/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.thinkgem.jeesite.common.persistence.DataEntity;
import org.hibernate.validator.constraints.Length;

import java.math.BigDecimal;
import java.util.Date;

/**
 * 订单Entity
 * @author zhajing
 * @version 2015-09-17
 */
public class SysOrderSummary{

	//状态
	private String status;
	//订单数量
	private Integer statusCount;
	//订单金额
	private BigDecimal statusAmout;
	//订单总金额
	private BigDecimal summaryAmout;

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public Integer getStatusCount() {
		return statusCount;
	}

	public void setStatusCount(Integer statusCount) {
		this.statusCount = statusCount;
	}

	public BigDecimal getStatusAmout() {
		return statusAmout;
	}

	public void setStatusAmout(BigDecimal statusAmout) {
		this.statusAmout = statusAmout;
	}

	public BigDecimal getSummaryAmout() {
		return summaryAmout;
	}

	public void setSummaryAmout(BigDecimal summaryAmout) {
		this.summaryAmout = summaryAmout;
	}
}