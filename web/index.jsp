<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>用户登录页面</title>
    <%@include file="/pages/common/head.jsp"%>
    <script type="text/javascript">
        $(function () {
            $("#username").blur(function () {
                var username=this.value;
                $.getJSON("http://localhost:8080/book/userServlet","action=existsUsername&username="+username,function (data) {
                    if (data.existsUsername=="t") {
                        $("span.errorMsg").text("用户名存在！");
                    } else {
                        $("span.errorMsg").text("用户名不存在！");
                    }
                });

            });
        });
    </script>

</head>
<body>


<div class="login_banner">
    <div id="login_header">
        <img class="logo_img" alt="" src="static/img/logo.gif" >
    </div>

    <div id="content">
        <div class="login_form">
            <div class="login_box">
                <div class="tit">
                    <h1>用户登录</h1>
                </div>
                <div class="msg_cont">
								<span class="errorMsg">
									请输入账号和密码
								</span>
                </div>
                <div class="form">
                    <form action="userServlet" method="post">
                        <input type="hidden" name="action" value="login">
                        <label>账号：</label>
                        <input class="itxt" type="text" placeholder="请输入用户名"
                               autocomplete="off" tabindex="1" name="username" id="username"/>
                        <br />
                        <br />
                        <label>密码：</label>
                        <input class="itxt" type="password" placeholder="请输入密码"
                               autocomplete="off" tabindex="1" name="password" />
                        <br />
                        <br />
                        <input type="submit" value="登录" id="sub_btn" />
                    </form>
                </div>

            </div>
        </div>
    </div>
</div>
</body>
</html>