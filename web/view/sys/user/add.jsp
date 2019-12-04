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
    <script src="${path}/static/jquery-validation-1.9.0/jquery.validate.js"></script>
</head>
<style>
    .error {
        color: red;
    }
</style>
<body>
<%@include file="/view/common/head.jsp" %>
<div>
    <%@include file="/view/common/menu.jsp" %>
    <div style="border: black 1px solid;width: 87%; height: 85%;float: right">
        <div class="container" style="width: 500px">

            <form action="/sys/user/add" method="get" id="form-add">
                <div class="form-group">
                    <label>部门：</label>
                    <select id="sel-dept" class="form-control" name="deptId">

                    </select>
                </div>
                <div class="form-group">
                    <label>账户：</label>
                    <input type="text" class="form-control" id="account" name="account">
                </div>
                <div class="form-group">
                    <label>密码：</label>
                    <input type="text" class="form-control" id="password" name="password">
                </div>
                <div class="form-group">
                    <label>姓名：</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label>邮箱：</label>
                    <input type="text" class="form-control" id="email" name="email">
                </div>
                <div class="form-group">
                    <label>年龄：</label>
                    <input type="text" class="form-control" id="age" name="age">
                </div>
                <div class="form-group">
                    <label>性别：</label>
                    <input type="radio" name="sex" value="1" checked>男
                    <input type="radio" name="sex" value="0">女
                </div>
                <div class="form-group">
                    <label>出生日期：</label>
                    <input type="date" class="form-control" id="birthDate" name="birthDate" required>
                </div>

                <div class="text-center">
                    <input type="submit" class="btn btn-success" value="保存">
                    <button type="reset" class="btn btn-default">重置</button>
                    <a href="/sys/user/list" class="btn btn-danger">返回</a>
                </div>

            </form>
        </div>

    </div>
</div>
<script>
    $(function () {
        $.ajax({
            url: "/sys/dept/list",
            data: "",
            type: "get",
            dataType: "json",
            success: function (data) {
                var html = '<option value="-1" selected>请选择</option>';
                for (var i = 0; i < data.length; i++) {
                    html += '<option value="' + data[i].id + '" >' + data[i].name + '</option>';
                }
                $("#sel-dept").append(html);
            }
        });

        $.noConflict();
        $.validator.addMethod("checkAge", function (value, element, params) {
            var reg = /^[0-9]{1,3}$/;
            if (reg.test(value)) {
                return true;
            } else {
                return false;
            }
        });
        $.validator.addMethod("checkDept", function (value, element, params) {
            if (value!=-1) {
                return true;
            } else {
                return false;
            }
        });

        $("#form-add").validate({
            rules: {
                deptId:{
                    checkDept:""
                },
                account: {
                    required: true
                },
                password: {
                    required: true,
                    rangelength: [4, 8]
                },
                email: {
                    required: true,
                    email: true
                },
                age: {
                    checkAge:""
                }

            },
            messages:{
                deptId:{
                    checkDept:"请选择部门"
                },
                account: {
                    required: "请输入账号"
                },
                password: {
                    required:"必填信息",
                    rangelength: "密码长度4-8位"
                },
                email: {
                    required:"请输入邮箱",
                    email: "邮箱格式不对"

                },
                age: {
                    age: {
                        checkAge:"请输入正确的年龄"
                    }
                }
            }
        });

    });
</script>
</body>
</html>
