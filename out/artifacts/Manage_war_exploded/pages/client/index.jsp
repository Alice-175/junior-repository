<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>首页</title>
	<%@include file="/pages/common/head.jsp"%>
	<script type="text/javascript">
		$(function () {

			$("button.addToCart").click(function () {
				confirm("你确定要借阅【"+$(this).parent().parent().parent().find("td:first").text()+"】吗？");
				var bookid=$(this).attr("bookId");
				var newurl="http://localhost:8080/book/cartServlet?action=addCart&id="+bookid;
				location.href=newurl;//l小写，修改一个常量
			});
		})
	</script>
</head>
<body>
<div class="index2_banner">
	<div id="header">
			<span class="wel_word">书库</span>
        <div class="book_cond">
            <form action="client/ClientBookServlet" method="get">
                <input type="hidden" name="action" value="pageByname">
                图书名称：<input id="findbookname" type="text" name="findbookname" value="${param.findbookname}">
                <input type="submit" value="查询" />
            </form>
        </div>
			<div>
				<c:if test="${empty sessionScope.user}">
					<a href="pages/repage/login.jsp">登录</a> |
				</c:if>
				<c:if test="${sessionScope.user.name eq 'admin'}">
					<span>用户(管理员)：<span class="um_span">${sessionScope.user.name}</span></span>
					<a href="pages/borrow/cart.jsp">我的书架</a>
					<a href="pages/repage/borrow.jsp">我的借阅</a>
					<a href="pages/repage/manager.jsp">图书管理</a>
					<a href="userServlet?action=logout">退出</a>&nbsp;&nbsp
				</c:if>
				<c:if test="${sessionScope.user.name ne 'admin'}">
					<span>用户：<span class="um_span">${sessionScope.user.name}</span></span>
					<a href="pages/borrow/cart.jsp">我的书架</a>
					<a href="pages/repage/borrow.jsp">我的借阅</a>
					<a href="pages/repage/re.jsp">书库</a>
					<a href="userServlet?action=logout">退出</a>&nbsp;&nbsp
				</c:if>


			</div>
	</div>
	<div id="main">
		<table>
				<tr>
					<td class="label-L">书名</td>
					<td class="label-L">作者</td>
					<td class="label-L">单价</td>
					<td class="label-L">库存</td>
				</tr>

			<c:forEach items="${requestScope.page.items}" var="book">
			<tr>
				<td>${book.name}</td>
				<td>${book.author}</td>
				<td>${book.price}</td>
				<td>${book.stock}</td>
				<td><div class="book_add">
					<button bookId="${book.id}" class="addToCart">拿取</button>
				</div></td>
			</tr>
			</c:forEach>

		</div>

		</table>
	<%@include file="/pages/common/page_nav.jsp"%>
	</div>

</body>
</html>