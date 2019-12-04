<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/2
  Time: 13:13
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
    <div style="border: black 1px solid;width: 87%; height: 85%;float: right">
        <form action="/sys/user/list">
            账户：<input type="text" name="account" value="${account}">
            开始时间：<input type="date" name="startTime" value="">
            结束时间：<input type="date" name="endTime" value="">

            <input type="submit" value="查询" class="btn btn-success">
            <a href="/view/sys/user/add.jsp" class="btn btn-warning ">添加</a>
        </form>
        <table class="table table-striped table-bordered table-hover">
            <tr>
                <th>序号</th>
                <th>部门名称</th>
                <th>账号</th>
                <th>姓名</th>
                <th>年龄</th>
                <th>性别</th>
                <th>出生日期</th>
                <th>创建时间</th>
                <th>操作</th>
            </tr>
            <c:forEach var="user" items="${list}" varStatus="status">
                <tr>
                    <td>${status.index+1}</td>
                    <td>${user.deptName}</td>
                    <td>${user.account}</td>
                    <td>${user.name}</td>
                    <td>${user.age}</td>
                    <td>
                        <c:if test="${user.sex==1}">男</c:if>
                        <c:if test="${user.sex==0}">女</c:if>
                    </td>
                    <td>
                        <fmt:parseDate var="birthDate" value="${user.birthDate}"
                                       pattern="yyy-MM-dd"></fmt:parseDate>
                        <fmt:formatDate value="${birthDate}" pattern="yyyy年MM月dd日"></fmt:formatDate>
                    </td>
                    <td>
                        <fmt:parseDate var="createTime" value="${user.createTime}"
                                       pattern="yyy-MM-dd HH:mm:ss"></fmt:parseDate>
                        <fmt:formatDate value="${createTime}" pattern="yyy-MM-dd HH:mm:ss"></fmt:formatDate>
                    </td>
                    <td>
                        <a href="/sys/user/getUserById?id=${user.id}" class="btn btn-warning">修改</a>
                        <a href="/sys/user/deleteById?id=${user.id}" class="btn btn-danger">删除</a>
                    </td>
                </tr>

            </c:forEach>
        </table>
        <a href="/sys/user/list?account=${account}&page=1" class="btn btn-info">首页</a>
        <a href="/sys/user/list?account=${account}&page=${page.pageCurrent<=1?1:(page.pageCurrent-1)}"
           class="btn btn-info">上一页</a>
        <a href="/sys/user/list?account=${account}&page=${page.pageCurrent>=page.pageCount?page.pageCount:(page.pageCurrent+1)}"
           class="btn btn-info">下一页</a>
        <a href="/sys/user/list?account=${account}&page=${page.pageCount}" class="btn btn-info">尾页</a>
        当前页：${page.pageCurrent} 总页数：${page.pageCount} 总数据：${page.count}

    </div>
</div>
</body>
</html>
