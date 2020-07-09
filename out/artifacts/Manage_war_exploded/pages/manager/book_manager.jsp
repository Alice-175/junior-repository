<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>图书管理</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {
			$("a.delectClass").click(function () {
				return confirm("你确定要删除《"+$(this).parent().parent().find("td:first").text()+"》吗？");
			});
		})
	</script>
</head>
<body>
<div class="index2_banner">
	<div id="header">
			<img class="logo_img" alt="" src="../../static/img/logo.gif" >
			<span class="wel_word">图书管理</span>
		<%@include file="/pages/common/manager_menu.jsp"%>
	</div>
	
	<div id="main">
		<table>
			<tr>
				<td class="label-L">书名</td>
				<td class="label-L">价格</td>
				<td class="label-L">作者</td>
				<td class="label-L">库存</td>
				<td colspan="2">操作</td>
			</tr>
			<c:forEach items="${requestScope.page.items}" var="book">
				<tr>
					<td>${book.name}</td>
					<td>${book.price}</td>
					<td>${book.author}</td>
					<td>${book.stock}</td>
					<td><a href="manager/bookServlet?action=getBook&id=${book.id}&method=update&pageNo=${requestScope.page.pageNo}">修改</a></td>
					<td><a class="delectClass" href="manager/bookServlet?action=delect&id=${book.id}&pageNo=${requestScope.page.pageNo}">删除图书</a></td>
				</tr>
			</c:forEach>
			
			<tr>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td></td>
				<td><a href="pages/manager/book_edit.jsp?method=add&pageNo=${requestScope.page.pageNo}">添加图书</a></td>
			</tr>	
		</table>
		<%@include file="/pages/common/page_nav.jsp"%>
	</div>
</div>
</body>
</html>