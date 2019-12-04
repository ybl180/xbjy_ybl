<%--
  Created by IntelliJ IDEA.
  User: DFG
  Date: 2019/11/29
  Time: 12:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<%--<link rel="stylesheet" href="/static/bootstrap/css/bootstrap.css">--%>
<%--<script src="/static/js/jquery-3.3.1.js"></script>--%>
<%--<script src="/static/bootstrap/js/bootstrap.js"></script>--%>
<%--<style>--%>
    <%--li {--%>
        <%--list-style: none;--%>
    <%--}--%>
<%--</style>--%>
<script>
    $(function () {
        // $.ajax({
        //     url:"/sys/menu",
        //     data:"",
        //     type:"get",
        //     dataType:"json",
        //     success:function (data) {
        //         var html="";
        //         for (var i = 0; i <data.length ; i++) {
        //             if(data[i].type==1){
        //                 html+=data[i].name+'<br>';
        //                 html+='<ul>'
        //                 for (var j = 0; j <data.length ; j++) {
        //                     if(data[j].pId==data[i].id){
        //                         html+='<li><a href="'+data[j].menuUrl+'">'+data[j].name+'</a></li>'
        //                     }
        //                 }
        //                 html+='</ul>';
        //             }
        //         }
        //         $("#div-menu").append(html);
        //     }
        // });


        $.ajax({
            url: "/sys/menu",
            data: "",
            type: "get",
            dataType: "json",
            success: function (data) {
                var html = '';
                var parent = data.parent;
                var son = data.son;
                for (var i = 0; i < parent.length; i++) {
                    html += parent[i].name + '<br>';
                    html += '<ul>';
                    for (var j = 0; j < son.length; j++) {
                        if (parent[i].id == son[j].pId) {
                            html += '<li><a href="' + son[j].menuUrl + '">' + son[j].name + '</a></li>';
                        }
                    }
                    html += "</ul>"
                }
                $("#div-menu").append(html);
            },
            error: function () {

            }
        });

    });
</script>
<body>
<div id="div-menu" style="border: black 1px solid;width: 10%; height: 85%;float: left;">

</div>
</body>
</html>
