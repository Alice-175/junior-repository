<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的借阅</title>
	<%@include file="/pages/common/head.jsp"%>
</head>
<body>
<div class="index2_banner">
	<div id="header">
			<span class="wel_word">我的书架</span>
		<div class="book_cond">
			<form action="client/ClientBookServlet" method="get">
				<input type="hidden" name="action" value="pageByname">
				图书名称：<input id="findbookname" type="text" name="findbookname" value="${param.findbookname}">
				<input type="submit" value="查询" />
			</form>
		</div>
		<%@include file="/pages/common/login_success_menu.jsp"%>
		<script type="text/javascript">
			$(function () {
				$("a.deleteItem").click(function () {
					return confirm("你确定要放回【"+$(this).parent().parent().find("td:first").text()+"】吗？");
				});
				$("#clean").click(function () {
					return confirm("你确定要全部放回图书吗？");
				});
				//输入框绑定失去焦点事件blur,输入框绑定改变事件change
				$(".updateCount").change(function () {
					var count=this.value;
					var id=$(this).attr('bookId');
					if(confirm("你确定要修改【"+$(this).parent().parent().find("td:first").text()+"】的数量为"+count+"吗？")){
						var newurl="http://localhost:8080/book2/cartServlet?action=updateCount&id="+id+"&count="+count;
						location.href=newurl;//l小写，修改一个常量
					}else{
						this.value=this.defaultValue;
					}
				});
			})
		</script>
	</div>
	
	<div id="main">
	
		<table>
			<tr>
				<td class="label-L">书名</td>
				<td class="label-L">作者</td>
				<td class="label-L">数量</td>
				<td class="label-L">价格</td>
				<td class="label-L">操作</td>
			</tr>
			<c:if test="${not empty sessionScope.cart.items}">
				<c:forEach items="${sessionScope.cart.items}" var="entry">
					<tr>
						<td>${entry.value.name}</td>
						<td>${entry.value.author}</td>
						<td>${entry.value.count}</td>
						<td>${entry.value.price}</td>
						<td><a class="deleteItem" href="cartServlet?action=deleteItem&id=${entry.value.id}">放回</a></td>
					</tr>
				</c:forEach>
			</c:if>
			<c:if test="${empty sessionScope.cart.items}">
				<tr>
					<td colspan="5"><a href="pages/repage/re.jsp">书架空空如也</a></td>
				</tr>
			</c:if>

			
		</table>
		<c:if test="${not empty sessionScope.cart.items}">
		<div class="cart_info">
			<span id="clean" class="cart_span"><a href="cartServlet?action=clean">全部放回</a></span>
			<span class="cart_span"><a href="orderServlet?action=addOrder">全部借阅</a></span>
		</div>
		</c:if>
	</div>
</div>
</body>
</html>