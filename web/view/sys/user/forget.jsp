<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/12/3
  Time: 16:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<div class="container" style="width: 400px">
    <form action="/sys/user/forgetPassword" mehtod="get">
        账号：<input type="text" name="account"><br><br>
        密码：<input type="text" name="password"><br><br>
        邮箱：<input type="text" name="email" id="email">
        <a class="btn btn-info" id="btn_sendEmail">发送</a><span id="timeStr"></span><br><br>
        验证码：<input type="text" name="code" value=""><br><br>

        <input type="submit" value="提交" class="btn btn-success ">
    </form>
</div>
</body>
<script>

    var downTime;
    $("#btn_sendEmail").click(function () {
        //开始60秒倒计时
        downTime = window.setInterval("changeTime()", 1000);
        $("#btn_sendEmail").attr("disabled", true);

        var email = $("#email").val();
        $.ajax({
            url: "/sys/email/sendEmail",
            data: {email: email},
            type: "get",
            dateType: "text",
            success: function (data) {
                alert(data)
            }
        });
    });
    var time = 60;

    function changeTime() {
        time--;
        $("#timeStr").text(time);
        if (time == 0) {
            $("#timeStr").text("");
            time = 60;
            clearInterval(downTime);
            $("#btn_sendEmail").attr("disabled", false);
        }
    }


    // var time = 60;
    // //定时器
    // var dsq;
    //
    // $(function () {
    //     $("#btn_sendEmail").click(function () {
    //
    //         dsq = window.setInterval("changeTime()", 1000);
    //         $("#btn_sendEmail").attr("disabled", "disabled");
    //
    //         var email = $("#email").val();
    //         $.ajax({
    //             url: "/sys/email/sendEmail",
    //             data: {email: email},
    //             type: "get",
    //             dataType: "text",
    //             success: function (data) {
    //                 alert(data);
    //             }
    //         });
    //
    //     });
    // });
    //
    // function changeTime() {
    //     --time;
    //     $("#timeStr").text(time);
    //     if (time == 0) {
    //         $("#timeStr").text("");
    //         time = 60;
    //         window.clearInterval(dsq);
    //         $("#btn-send").attr("disabled", null);
    //     }
    // }
</script>
</html>
