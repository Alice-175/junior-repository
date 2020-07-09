<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div>
    <span>用户：<span class="um_span">${sessionScope.user.name}</span></span>
    <a href="pages/repage/borrow.jsp">我的借阅</a>
    <a href="pages/borrow/cart.jsp">我的书架</a>
    <a href="pages/repage/re.jsp">返回书库</a>
    <a href="userServlet?action=logout">退出</a>&nbsp;&nbsp;

</div>
