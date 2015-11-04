/**
 * Copyright &copy; 2012-2014 <a href="https://github.com/thinkgem/jeesite">JeeSite</a> All rights reserved.
 */
package com.thinkgem.jeesite.modules.order.entity;

import com.thinkgem.jeesite.common.utils.DateUtils;
import com.thinkgem.jeesite.common.utils.excel.annotation.ExcelField;
import org.hibernate.validator.constraints.Length;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;

import com.thinkgem.jeesite.common.persistence.DataEntity;

/**
 * 订单Entity
 * @author zhajing
 * @version 2015-09-17
 */
public class SysOrder extends DataEntity<SysOrder> {
	
	private static final long serialVersionUID = 1L;

	public static final String SEARCH_TYPE_YES = "Yesteday";

	public static final String SEARCH_TYPE_TODAY = "Today";

	public static final String SEARCH_TYPE_WEEK = "Week";

	public static final String SEARCH_TYPE_MONTH = "Month";

	private String cid;		// CID
	private String producttype;		// 商品类型
	private String product;		// 商品名称
	private Date orderDate;		// 下单时间
	private Double amount;		// 商品金额
	private String customerName;		// 客户名称
	private String customerPhone;		// 客户电话
	private String customerAddress;		// 收货地址
	private String orderName;		// 订购套餐
	private String status;		// 订单状态
	private String operremarks;		// 客服备注信息
	private Date beginOrderDate;		// 开始 下单时间
	private Date endOrderDate;		// 结束 下单时间

	private String linkAddr; //下单页面

	private String orderSource; //下单来路

	private String orderIP; //下单IP

	private String custRemark;

	private String searchType; //过滤模式
	
	public SysOrder() {
		super();
	}

	public SysOrder(String id){
		super(id);
	}

	@Length(min=0, max=32, message="CID长度必须介于 0 和 32 之间")
//	@ExcelField(title="CID", type=1, align=1)
	public String getCid() {
		return cid;
	}

	public void setCid(String cid) {
		this.cid = cid;
	}
	
	@Length(min=0, max=64, message="商品类型长度必须介于 0 和 64 之间")
	public String getProducttype() {
		return producttype;
	}

	public void setProducttype(String producttype) {
		this.producttype = producttype;
	}
	
	@Length(min=0, max=64, message="商品名称长度必须介于 0 和 64 之间")
	@ExcelField(title="订购产品", type=1, align=1, sort = 5)
	public String getProduct() {
		return product;
	}

	public void setProduct(String product) {
		this.product = product;
	}

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	public Date getOrderDate() {
		return orderDate;
	}

	@ExcelField(title="创建时间", type=1, align=1, sort=7)
	public String getOrderDateStr() {
		return DateUtils.formatDate(this.orderDate, "yyyy/MM/dd HH:mm");
	}

	public void setOrderDate(Date orderDate) {
		this.orderDate = orderDate;
	}

	public Double getAmount() {
		return amount;
	}

	@ExcelField(title="金额", type=1, align=1, sort = 6)
	public String getAmountStr() {
		return String.valueOf(amount);
	}
	public void setAmount(Double amount) {
		this.amount = amount;
	}
	
	@Length(min=0, max=64, message="客户名称长度必须介于 0 和 64 之间")
	@ExcelField(title="联系人", type=1, align=1, sort = 0)
	public String getCustomerName() {
		return customerName;
	}

	public void setCustomerName(String customerName) {
		this.customerName = customerName;
	}
	
	@Length(min=0, max=64, message="客户电话长度必须介于 0 和 64 之间")
	@ExcelField(title="电话1", type=1, align=1, sort = 1)
	public String getCustomerPhone() {
		return customerPhone;
	}

	@ExcelField(title="电话2", type=1, align=1, sort = 2)
	public String getCustomerPhone2() {
		return "";
	}

	@ExcelField(title="电话3", type=1, align=1, sort = 3)
	public String getCustomerPhone3() {
		return "";
	}

	public void setCustomerPhone(String customerPhone) {
		this.customerPhone = customerPhone;
	}
	
	@Length(min=0, max=512, message="收货地址长度必须介于 0 和 512 之间")
	@ExcelField(title="地址", type=1, align=1, sort = 4)
	public String getCustomerAddress() {
		return customerAddress;
	}

	public void setCustomerAddress(String customerAddress) {
		this.customerAddress = customerAddress;
	}
	
	@Length(min=0, max=256, message="订购套餐长度必须介于 0 和 256 之间")
	public String getOrderName() {
		return orderName;
	}

	public void setOrderName(String orderName) {
		this.orderName = orderName;
	}
	
	@Length(min=0, max=13, message="订单状态长度必须介于 0 和 13 之间")
//	@ExcelField(title="订单状态", type=1, align=1, dictType = "ordState")
	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
	
	@Length(min=0, max=512, message="客服备注信息长度必须介于 0 和 512 之间")
	public String getOperremarks() {
		return operremarks;
	}

	public void setOperremarks(String operremarks) {
		this.operremarks = operremarks;
	}
	
	public Date getBeginOrderDate() {
		return beginOrderDate;
	}

	public void setBeginOrderDate(Date beginOrderDate) {
		this.beginOrderDate = beginOrderDate;
	}
	
	public Date getEndOrderDate() {
		return endOrderDate;
	}

	public void setEndOrderDate(Date endOrderDate) {
		this.endOrderDate = endOrderDate;
	}

	public String getLinkAddr() {
		return linkAddr;
	}

	public void setLinkAddr(String linkAddr) {
		this.linkAddr = linkAddr;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}

	@Length(min=0, max=512, message="下单来路长度必须介于 0 和 512 之间")
	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}
	@Length(min=0, max=64, message="下单IP长度必须介于 0 和 64 之间")
	public String getOrderIP() {
		return orderIP;
	}

	public void setOrderIP(String orderIP) {
		this.orderIP = orderIP;
	}

	@ExcelField(title="备注", type=1, align=1, sort = 8)
	public String getCustRemark() {
		return "cid=" + this.cid + "下单时间：" + DateUtils.formatDateTime(this.orderDate) + "备注：" + this.remarks;
	}

	public void setCustRemark(String custRemark) {
		this.custRemark = custRemark;
	}
}