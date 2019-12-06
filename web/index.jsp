<%@ page import="com.ybl.sys.constants.SysConstants" %>
<%@ page import="java.net.URL" %>
<%@ page import="java.net.URLDecoder" %>
<%@ page import="com.alibaba.fastjson.JSON" %>
<%@ page import="com.alibaba.fastjson.TypeReference" %>
<%@ page import="com.ybl.sys.entity.User" %><%--
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
<%
    Cookie[] cookies = request.getCookies();
    User loginUser = new User();
    if (cookies != null) {
        for (int i = 0; i < cookies.length; i++) {
            if (SysConstants.COOKIE_LOGIN_USER.equals(cookies[i].getName())) {
                //获取cookie中的值
                String cookieValues = cookies[i].getValue();
                //解码
                String jsonDecode = URLDecoder.decode(cookieValues, "utf-8");
                //转化成json对象
                loginUser = JSON.parseObject(jsonDecode, new TypeReference<User>() {
                });
            }
        }
    }
%>

<div class="container" style="width: 400px">
    <h2>登录</h2>
    <form action="/sys/login/login" method="get">
        账号：<input type="text" name="account" value="<%=loginUser.getAccount()==null?"":loginUser.getAccount()%>" id="account"><br><br>
        密码：<input type="text" name="password" value="<%=loginUser.getPassword()==null?"":loginUser.getPassword()%>" id="password"><br><br>
        验证码：<input type="text" name="picCode" value="">
        <img src="/sys/login/getPic" alt="无法加载" id="img" onclick="getPic()"><br><br>
        7天免登录：<input type="checkbox" name="remember" value="checked"><br><br>
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
