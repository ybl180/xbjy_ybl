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

        <div class="content">
            <form action="/sys/dept/listAll">
                部门名称：<input type="text" name="deptName" value="${deptName}">
                <input type="submit" value="查询" class="btn btn-success">
                <a href="/view/sys/dept/addDept.jsp" class="btn btn-danger">添加</a>
            </form>
            <table class="table table-bordered table-striped">
                <tr>
                    <th>序号</th>
                    <th>部门名称</th>
                    <th>部门人数</th>
                    <th>创建时间</th>
                    <th>创建人</th>
                    <th>操作</th>
                </tr>
                <c:forEach var="list" items="${deptList}" varStatus="status">
                    <tr>
                        <td>${status.index+1}</td>
                        <td>${list.name}</td>
                        <td>${list.deptCount}</td>
                        <td>
                            <fmt:parseDate var="createTime" value="${list.createTime}"
                                           pattern="yyyy-MM-dd HH:mm:ss"></fmt:parseDate>
                            <fmt:formatDate value="${createTime}" pattern="yyyy-MM-dd HH:mm:ss"></fmt:formatDate>
                        </td>
                        <td>${list.createPerson}</td>
                        <td>
                            <a href="/sys/dept/getDeptById?id=${list.id}" class="btn btn-primary">修改</a>
                                <%--<a href="/sys/dept/deleteById?id=${list.id}" class="btn btn-primary">删除</a>--%>
                            <button class="btn btn-primary" onclick="btnClick(${list.id})">删除
                            </button>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <a class="btn btn-danger" href="/sys/dept/listAll?deptName=${deptName}&pageCurrent=1">首页</a>
            <a class="btn btn-danger"
               href="/sys/dept/listAll?deptName=${deptName}&pageCurrent=${(page.pageCurrent-1)<=0?1:(page.pageCurrent-1)}">上一页</a>
            <a class="btn btn-danger"
               href="/sys/dept/listAll?deptName=${deptName}&pageCurrent=${(page.pageCurrent+1)>=page.pageCount?page.pageCount:(page.pageCurrent+1)}">下一页</a>
            <a class="btn btn-danger" href="/sys/dept/listAll?deptName=${deptName}&pageCurrent=${page.pageCount}">尾页</a>
            当前页：${page.pageCurrent} 总页数：${page.pageCount}总数据：${page.count}
        </div>
    </div>
</div>
</body>
<script>
    function btnClick(deptId) {
        $.ajax({
            url: "/sys/dept/deleteById",
            data: {id: deptId},
            type: "get",
            dataType: "text",
            success: function (data) {
                if ("400" == data) {
                    alert("删除失败，部门下存在用户");
                } else if ("200" == data) {
                    alert("删除成功");
                }
                window.location.href = "/sys/dept/listAll"
            }
        });
    }
</script>
</html>
