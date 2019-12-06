<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">
<script src="/static/js/jquery-3.3.1.js"></script>
<script src="/static/bootstrap/js/bootstrap.js"></script>
<body>
<div style=" width:100%; height: 10%; border: black 1px solid;">
    <span class="h3" >在线人数:${applicationScope.applicationLoginCount}</span>

    <form action="/sys/login/logout" >
        <input type="submit" value="退出" class="btn btn-danger">
    </form>
</div>
</body>
</html>