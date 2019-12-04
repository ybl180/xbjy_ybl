<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/11/29
  Time: 12:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>$Title$</title>
</head>
<body>
<div class="container" style="width: 400px">
    <h2>登录</h2>
    <form action="/sys/login/login" method="get">
        账号：<input type="text" name="account" value="" id="account"><br><br>
        密码：<input type="text" name="password" value="" id="password"><br><br>
        验证码：<input type="text" name="picCode" value="">
        <img src="/sys/login/getPic" alt="无法加载" id="img" onclick="getPic()"><br><br>
        7天免登录：<input type="checkbox" name="remember" value="1"><br><br>
        <input type="submit" name="" value="登录">
    </form>

    <a href="${path}/view/sys/user/forget.jsp">忘记密码？</a>
</div>
</body>
<script>
    function getPic() {
        // $("#img").attr("src", $("#img").attr("src") + "?nocache="+new Date().getTime());
        document.getElementById("img").src = document.getElementById("img").src + "?nocache=" + new Date().getTime();
    }
</script>
</html>
