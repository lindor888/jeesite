<%@ page import="com.thinkgem.jeesite.modules.order.entity.SysOrder" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp"%>
<html>
<head>
	<style>
		li.filter{
			height:32px;
			float:right;
			margin-right:3px;
			border:1px solid red;
		}

		li.fiterActive {
			height:32px;
			float:right;
			margin-right:3px;
			border:1px solid red;
		}

		li.fiterActive a{
			text-decoration:none;
			display:block;
			padding:5px;
			margin: 0px;
		}

		li.filter a{
			text-decoration:none;
			display:block;
			background:yellow;
			padding:5px;
			margin: 0px;
		}
		.summaryDiv{line-height: 31px; width: 100%}
		.summaryDiv:hover div{display: none; -webkit-transition:-webkit-transform 1s ease;}
		.summary{position: fixed; bottom: 0; background-color: #45aeea; line-height: 31px; width: 100%}
		.summary div.item{  display: inline-block; border-left: 1px solid rgba(34,36,38,.15); margin-right: 25px; padding: 5px  }
		.item label{font-weight: bold}

	</style>
	<title>订单管理</title>
	<meta name="decorator" content="default"/>
	<script type="text/javascript">
		$(document).ready(function() {

			$("#${sysOrder.searchType}"+"_li").removeClass("filter").addClass("fiterActive");

			$("#btnExport").click(function(){
				var selectValues = getSelectedValues();
				if (selectValues == "") {
					top.$.jBox.alert("请选择要导出的订单！");
					return;
				}

				top.$.jBox.confirm("确认要导出订单数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){

						$("#searchForm").attr("action","${ctx}/order/sysOrder/export");
						$("#selectedValue").val(selectValues);
						$("#searchForm").submit();
						$("#searchForm").attr("action","${ctx}/order/sysOrder/");
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});

			$("#btnBatchDel").click(function(){
				var selectValues = getSelectedValues();
				if (selectValues == "") {
					top.$.jBox.alert("请选择要删除的订单！");
					return;
				}

				top.$.jBox.confirm("确认要删除选中的订单数据吗？","系统提示",function(v,h,f){
					if(v=="ok"){

						$("#searchForm").attr("action","${ctx}/order/sysOrder/batchDelete");
						$("#selectedValue").val(selectValues);
						$("#searchForm").submit();
					}
				},{buttonsFocus:1});
				top.$('.jbox-body .jbox-icon').css('top','55px');
			});

			//选择
			$("#checkAll").click(function() {
				$('input[name="subBox"]').attr("checked",this.checked);
			});

			var $subBox = $("input[name='subBox']");
			$subBox.click(function(){
				$("#checkAll").attr("checked",$subBox.length == $("input[name='subBox']:checked").length ? true : false);
			});
		});


		function getSelectedValues() {
			var value = "";
			$("input[name='subBox']:checked").each(function(index) {
				if (value != "") {
					value += ",";
				}
				value += this.value;
			});
			return value;
		}

		function page(n,s){
			$("#pageNo").val(n);
			$("#pageSize").val(s);
			$("#searchForm").submit();
        	return false;
        }

		function clearSearchfield() {
			$("#cid").val("");
			$("#product").val("");
			$("#beginOrderDate").val("");
			$("#endOrderDate").val("");
			$("#customerName").val("");
			$(".select2-chosen", $("[id$='_status']")).text("");
			$("#status").get(0).selectedIndex=-1;
		}

		function filter(searchType) {
			$("#beginOrderDate").val("");
			$("#endOrderDate").val("");
			$("#searchForm").append('<input id="searchType" name="searchType" type="hidden" value="' + searchType + '"/>');
			$("#searchForm").submit();
		}


	</script>
</head>
<body>

	<ul class="nav nav-tabs">
		<li class="active"><a href="${ctx}/order/sysOrder/">订单列表</a></li>
		<shiro:hasPermission name="order:sysOrder:edit"><li><a href="${ctx}/order/sysOrder/form">订单添加</a></li></shiro:hasPermission>

		<li id="<%=SysOrder.SEARCH_TYPE_MONTH%>_li" class="filter"><a href="javascript:void(0)" onclick="filter('<%=SysOrder.SEARCH_TYPE_MONTH%>')">本月</a></li>
		<li id="<%=SysOrder.SEARCH_TYPE_WEEK%>_li" class="filter"><a href="javascript:void(0)" onclick="filter('<%=SysOrder.SEARCH_TYPE_WEEK%>')">本周</a></li>
		<li id="<%=SysOrder.SEARCH_TYPE_TODAY%>_li" class="filter"><a href="javascript:void(0)" onclick="filter('<%=SysOrder.SEARCH_TYPE_TODAY%>')">今天</a></li>
		<li id="<%=SysOrder.SEARCH_TYPE_YES%>_li" class="filter"><a href="javascript:void(0)" onclick="filter('<%=SysOrder.SEARCH_TYPE_YES%>')">昨天</a></li>
	</ul>

	<form:form id="searchForm" modelAttribute="sysOrder" action="${ctx}/order/sysOrder/" method="post" class="breadcrumb form-search">
		<input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
		<input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
		<input id="selectedValue" name="selectedValue" type="hidden" />
		<ul class="ul-form">
			<li><label>CID：</label>
				<form:input path="cid" htmlEscape="false" maxlength="32" class="input-medium"/>
			</li>
			<%--<li><label>商品类型：</label>--%>
				<%--<form:select path="producttype" class="input-medium">--%>
					<%--&lt;%&ndash;<form:option value="" label=""/>&ndash;%&gt;--%>
					<%--<form:options items="${fns:getDictList('proType')}" itemLabel="label" itemValue="value" htmlEscape="false"/>--%>
				<%--</form:select>--%>
			<%--</li>--%>
			<li><label>商品名称：</label>
				<form:input path="product" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>客户名称：</label>
			<form:input path="customerName" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>联系电话：</label>
			<form:input path="customerPhone" htmlEscape="false" maxlength="64" class="input-medium"/>
			</li>
			<li><label>订单状态：</label>
			<form:select path="status" class="input-medium">
				<form:option value="" label=""/>
				<form:options items="${fns:getDictList('ordState')}" itemLabel="label" itemValue="value" htmlEscape="false"/>
			</form:select>
			</li>
			<li><label>下单时间：</label>
				<input name="beginOrderDate" id="beginOrderDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${sysOrder.beginOrderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/> -
				<input name="endOrderDate" id="endOrderDate" type="text" readonly="readonly" maxlength="20" class="input-medium Wdate"
					   value="<fmt:formatDate value="${sysOrder.endOrderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>"
					   onclick="WdatePicker({dateFmt:'yyyy-MM-dd HH:mm:ss',isShowClear:false});"/>
			</li>
			<li class="btns"><input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/></li>
			<li class="btns"><input class="btn btn-info" type="button" onclick="clearSearchfield()" value="清空"/></li>
			<li class="btns"><input id="btnExport" class="btn btn-primary" type="button" value="导出"/></li>
			<li class="btns"><input id="btnBatchDel" class="btn btn-primary" type="button" value="批量删除"/></li>

			<li class="clearfix"></li>
		</ul>
	</form:form>
	<sys:message content="${message}"/>
	<table id="contentTable" class="table table-striped table-bordered table-condensed">
		<thead>
			<tr>
				<th><input id="checkAll" type="checkbox" />全选</th>
				<th>序号</th>
				<%--<th>ID</th>--%>
				<th>CID</th>
				<th>姓名</th>
				<th>手机</th>
				<th>收货地址</th>
				<th>订购商品</th>
				<th>订购时间</th>
				<th>订单状态</th>
				<shiro:hasPermission name="order:sysOrder:edit"><th style="widows: 80px;">处理</th><th>操作</th></shiro:hasPermission>
			</tr>
		</thead>
		<tbody>
		<c:forEach items="${page.list}" var="sysOrder" varStatus="status">
			<tr>
				<td><input name="subBox" type="checkbox" value="${sysOrder.id}" /></td>
				<td>${ status.index + 1}</td>
				<%--<td>${sysOrder.id}</td>--%>
				<td><a href="${ctx}/order/sysOrder/form?id=${sysOrder.id}">
					${sysOrder.cid}
				</a></td>
				<td>
					${sysOrder.customerName}
				</td>
				<td>
					<a href="${ctx}/order/sysOrder/edit?id=${sysOrder.id}">${sysOrder.customerPhone}</a>
				</td>
				<td>
					${sysOrder.customerAddress}
				</td>
				<td>
					${sysOrder.product}
				</td>
				<td>
					<fmt:formatDate value="${sysOrder.orderDate}" pattern="yyyy-MM-dd HH:mm:ss"/>
				</td>
				<td>
					${fns:getDictLabel(sysOrder.status, 'ordState', '')}
				</td>

				<shiro:hasPermission name="order:sysOrder:edit">
					<td>
						<select onchange="changeStatus('${sysOrder.id}', this.value)">
							<c:forEach items="${ordStateSelect}" var="r">
								<option value="${r.value}"
									<c:if test="${r.value==sysOrder.status}">selected</c:if> >${r.label}
								</option>
							</c:forEach>
						</select>
					</td>
					<td>
						<a href="${ctx}/order/sysOrder/edit?id=${sysOrder.id}">修改</a>|
						<a href="${ctx}/order/sysOrder/delete?id=${sysOrder.id}" onclick="return confirmx('确认要删除该订单吗？', this.href)">删除</a>
					</td>
				</shiro:hasPermission>
			</tr>
		</c:forEach>
		</tbody>
	</table>
	<div class="pagination" style="margin-bottom: 40px">${page}</div>

	<div class="summary">
		<div class="item count">
			<label>总计： </label> ${totalCount}个（${totalAmount}元）
		</div>
		<c:forEach items="${orderSummary}" var="sysOrderSummary">
			<div class="item">
				<label>${fns:getDictLabel(sysOrderSummary.status, 'ordState', '')}:</label> ${sysOrderSummary.statusCount} (${sysOrderSummary.statusAmout}元)
			</div>
		</c:forEach>
	</div>

	<script>
		function changeStatus(id, status) {
			top.$.jBox.confirm("您确定要执行操作吗？","系统提示",function(v,h,f){
				if(v=="ok"){
					location.href="${ctx}/order/sysOrder/save?id=" + id + "&status=" + status;
				}
			},{buttonsFocus:1});
			top.$('.jbox-body .jbox-icon').css('top','55px');
		}
	</script>
</body>
</html>