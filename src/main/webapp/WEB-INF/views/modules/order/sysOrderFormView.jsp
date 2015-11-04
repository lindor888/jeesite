<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>

	<style>
		.tbl-form{margin: 10px  }
		.tbl-form td{
			padding: 10px;
			border-bottom: 1px dotted #ddd;
		}
		.fieldName {width: 196px; text-align: right; word-break: keep-all; font-weight: bold}
	</style>
	<script type="text/javascript">
		$(document).ready(function() {
			//$("#name").focus();
			$("#inputForm").validate({

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
	<form:form id="inputForm" modelAttribute="sysOrder" action="${ctx}/order/sysOrder/changeStatus" method="post" class="form-horizontal">
		<form:hidden path="id"/>
		<sys:message content="${message}"/>

		<table class="tbl-form">
			<tr>
				<td class="fieldName">CID：</td>
				<td>${sysOrder.cid}</td>
				<td>&nbsp;</td>
				<td class="fieldName">商品类型：</td>
				<td>${fns:getDictValue(sysOrder.producttype, 'proType', '茶具')}</td>
			</tr>

			<tr>
				<td class="fieldName">商品名称：</td>
				<td nowrap="nowrap">${sysOrder.product}</td>
				<td>&nbsp;</td>
				<td class="fieldName">商品金额：</td>
				<td>${sysOrder.amount} （元）</td>
			</tr>

			<tr>
				<td class="fieldName">下单时间：</td>
				<td><fmt:formatDate value="${sysOrder.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/></td>
				<td>&nbsp;</td>
				<td class="fieldName">下单页面：</td>
				<td><a target="_blank" href="${sysOrder.linkAddr}">${sysOrder.linkAddr}</a></td>
			</tr>

			<tr>
				<td class="fieldName">下单来路：</td>
				<td>${sysOrder.orderSource}</td>
				<td>&nbsp;</td>
				<td class="fieldName">下单IP：</td>
				<td>${sysOrder.orderIP}</td>
			</tr>

			<tr>
				<td class="fieldName">客户名称：</td>
				<td>${sysOrder.customerName}</td>
				<td>&nbsp;</td>
				<td class="fieldName">客户电话：</td>
				<td>${sysOrder.customerPhone}</td>
			</tr>

			<tr>
				<td class="fieldName">收货地址：</td>
				<td colspan="4">${sysOrder.customerAddress}</td>
			</tr>

			<tr>
				<td class="fieldName">备注信息：</td>
				<td colspan="4">${sysOrder.remarks}</td>
			</tr>
		</table>

		<div class="control-group">
			<label class="control-label">订单状态：</label>
			<div class="controls">
				<form:select path="status" class="input-xlarge ">
					<form:options items="${fns:getDictList('ordState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
				</form:select>
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