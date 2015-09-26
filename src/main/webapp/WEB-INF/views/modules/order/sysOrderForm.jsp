<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({
				rules:{
					cid: {
						required: true
					},
					product: {
						required: true
					},
					orderDate: {
						required: true
					},
					amount: {
						required: true,
						number: true
					},
					customerName: {
						required: true
					},
					customerPhone: {
						mobile: true
					},
					customerAddress: {
						required: true
					}
				},

				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
						error.appendTo(element.parent().parent());
					} else {
						error.insertAfter(element);
					}
				}
			});
		});
	</script>
</head>
<body>
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/order/sysOrder/">订单列表</a></li>
		<li class="active"><a href="${ctx}/order/sysOrder/form?id=${sysOrder.id}">订单<shiro:hasPermission name="order:sysOrder:edit">${not empty sysOrder.id?'修改':'添加'}</shiro:hasPermission><shiro:lacksPermission name="order:sysOrder:edit">查看</shiro:lacksPermission></a></li>
	</ul><br/>
	<form:form id="inputForm" modelAttribute="sysOrder" action="${ctx}/order/sysOrder/save" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>		
		<div class="control-group">
			<label class="control-label">CID：</label>
			<div class="controls">
				<form:input path="cid" htmlEscape="false" maxlength="32" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品类型：</label>
			<div class="controls">
				<form:select path="producttype" class="input-xlarge ">
					<form:options items="${fns:getDictList('proType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品名称：</label>
			<div class="controls">
				<form:input path="product" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单时间：</label>
			<div class="controls">
				<input name="orderDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate "
					value="<fmt:formatDate value="${sysOrder.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">商品金额：</label>
			<div class="controls">
				<form:input path="amount" htmlEscape="false" class="number"/> 元
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户名称：</label>
			<div class="controls">
				<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客户电话：</label>
			<div class="controls">
				<form:input path="customerPhone" htmlEscape="false" maxlength="64" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">收货地址：</label>
			<div class="controls">
				<form:input path="customerAddress" htmlEscape="false" maxlength="512" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">订购套餐：</label>
			<div class="controls">
				<form:input path="orderName" htmlEscape="false" maxlength="256" class="input-xlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">下单页面：</label>
			<div class="controls">
				<a target="_blank" href="${sysOrder.linkAddr}">${sysOrder.linkAddr}</a>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('ordState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
			</div>
		</div>

		<div class="control-group">
			<label class="control-label">备注信息：</label>
			<div class="controls">
				<form:textarea path="remarks" htmlEscape="false" rows="4" maxlength="256" class="input-xxlarge "/>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">客服备注信息：</label>
			<div class="controls">
				<form:textarea path="operremarks" htmlEscape="false" rows="4" maxlength="512" class="input-xxlarge "/>
			</div>
		</div>
		<div class="form-actions">
			<shiro:hasPermission name="order:sysOrder:edit"><input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;</shiro:hasPermission>
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>