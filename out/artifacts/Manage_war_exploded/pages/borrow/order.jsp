<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>我的借阅</title>
	<%@include file="/pages/common/head.jsp"%>
<style type="text/css">
	h1 {
		text-align: center;
		margin-top: 200px;
	}
</style>
	<script type="text/javascript">
		$(function () {

			$("button.b_return").click(function () {
				confirm("你确定要归还【"+$(this).parent().parent().parent().find("td:first").text()+"】吗？");
				var bookname=$(this).attr("bookname");
				var newurl="http://localhost:8080/book/cartServlet?action=b_return&bookname="+bookname;
				location.href=newurl;//l小写，修改一个常量
			});
		})
	</script>
</head>
<body>
<div class="index2_banner">
	<div id="header">
			<span class="wel_word">我的借阅</span>
		<div class="book_cond">
			<form action="client/ClientBookServlet" method="get">
				<input type="hidden" name="action" value="pageByname">
				图书名称：<input id="findbookname" type="text" name="findbookname" value="${param.findbookname}">
				<input type="submit" value="查询" />
			</form>
		</div>
		<%@include file="/pages/common/login_success_menu.jsp"%>
	</div>

	<div id="main">
	<table>
		<tr>
			<td class="label-L">书名</td>
			<td class="label-L">借阅日期</td>
			<td class="label-L">归还日期</td>
			<td class="label-L">状态</td>
			<td class="label-L">操作</td>
		</tr>

		<c:forEach items="${requestScope.brrowpage.items}" var="book">
			<tr>
				<td>${book.bookname}</td>
				<td>${book.borrowdate}</td>
				<td>${book.returndate}</td>
				<c:if test="${book.status eq 1}">
					<td>未归还</td>
					<td><div class="book_add">
						<button bookname="${book.bookname}" class="b_return">归还</button>
					</div></td>
				</c:if>
				<c:if test="${book.status eq 0}">
					<td>已归还</td>
					<td></td>
				</c:if>
			</tr>
		</c:forEach>
	</table>
		<%@include file="/pages/common/borrowpage_nav.jsp"%>
	</div>

</div>
</body>
</html>