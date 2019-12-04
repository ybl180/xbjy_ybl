<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/4
  Time: 10:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%@include file="/view/common/head.jsp" %>
<div>
    <%@include file="/view/common/menu.jsp" %>
    <div class="container" style="width: 400px">
        <h3>修改部门</h3>
        <form action="/sys/dept/update">
            <input type="text" name="id" style="display: none" value="${dept.id}">
            <div class="form-group">
                <label>部门名称：</label>
                <input type="text" name="name" class="form-control" value="${dept.name}">
            </div>
            <div class="form-group">
                <label>创建人：</label>
                <label name="createBy"  class="form-control">${dept.createPerson}</label>
            </div>
            <input type="submit" value="提交" class="btn btn-success">
            <a href="/sys/dept/listAll" class="btn btn-danger">返回</a>
        </form>
    </div>
</div>
</body>
</html>
