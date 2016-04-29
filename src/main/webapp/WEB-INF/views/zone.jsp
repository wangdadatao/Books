<%@ page import="com.datao.pojo.Buy" %>
<%@ page import="java.util.List" %>
<%@ page import="com.datao.pojo.Zone" %>
<%@ page import="com.datao.pojo.Book" %>
<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/3/21
  Time: 19:47
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<html>
<head>
    <title>个人中心</title>
    <link rel="stylesheet" href="/Static/css/bootstrap.css">
    <script src="/Static/js/jquery-2.2.1.js"></script>
    <script src="/Static/js/bootstrap.min.js"></script>
    <script src="/Static/js/handlebars-v4.0.5.js"></script>

    <style>
        .wrap-nav {
            background-color: #000;
        }

        .navbar-inverse {
            padding: 0;
            margin: 0;
        }

        .head-img {
            height: 250px;
        }

        .head-img .bg-img {
            width: 100%;
            height: 100%;
        }

        .nav-left {
            margin: 30px 0 0 0;
            padding: 0;
            width: 250px;
        }

        .nav-left > li {
            height: 70px;
            border-bottom: 1px solid #cccccc;
            list-style: none;
            text-align: center;
        }

        .nav-left > li > p {
            color: #8c8c8c;
            font-size: 25px;
            font-family: 微软雅黑;
            line-height: 70px;
        }

        .nav-left > li:last-child {
            border-bottom: none;
        }

        .nav-left > li > p:hover {
            cursor: hand;
        }

        .nav-left > li:hover > p {
            color: #d26f9b;
        }

        .addactive {
            border-left: 3px solid #ff6aa8;
        }

        .addactive .show-right {
            display: block;
        }

        .wrap {
            position: relative;
        }

        .show-right {
            position: absolute;
            left: 360px;
            width: 700px;
            top: 30px;
            display: none;
        }

        .table th, td {
            text-align: center;
        }

        .a-addmoney {
            width: 300px;
            margin-top: 40px;
        }

        .a-out {
            width: 300px;
            margin: 80px 0 80px 0;
        }

        .show-head {
            position: absolute;
            top: 125px;
            left: 10%;
        }

        .show-head img {
            width: 100px;
            height: 100px;
        }

        .show-head td {
            text-align: left;
            font-family: 微软雅黑;
        }

        .div-photo {
            border: 1px solid #cccccc;
            width: 770px;
            padding-top: 10px;
        }

        .div-photo hr {
            margin: 5px;
        }

        .div-photo p {
            text-align: left;
        }

        .div-pictures {
            width: 200px;
            height: 150px;
            border: 5px solid #cccccc;
            padding: 3px;
            border-radius: 3px;
            margin: 14px;
            float: left;
        }

        .div-pictures img {
            width: 100%;
            height: 100%;
        }

        .remove-picture {
            position: absolute;
            margin: -17px 0 0 -17px;
            z-index: 5;
            display: none;
        }

    </style>
</head>
<body>
<%--导航--%>
<%@include file="public.jsp" %>

<div class="head-img">
    <img class="bg-img" src="/Static/img/zonebg.jpg">
    <div class="show-head">
        <table>
            <tr>
                <td>
                    <img src="http://7xsaoe.com1.z0.glb.clouddn.com/${requestScope.zone.headimg}" class="img-circle"
                         alt="头像">
                </td>
                <td>
                    <h2>${requestScope.zone.petname}</h2>
                </td>
            </tr>
        </table>
    </div>
</div>


<div class="container">
    <div class="wrap">
        <ul class="nav-left">
            <%--我的书籍--%>
            <li class="addactive"><p>我的书籍</p>
                <div class="show-right">
                    <table class="table tab-bordered">
                        <tr>
                            <th>书名</th>
                            <th>作者</th>
                            <th>出版社</th>
                            <th>类型</th>
                            <th>操作</th>
                        </tr>
                        <%
                            List<Buy> buys = (List<Buy>) request.getAttribute("buys");
                            List<Book> books = (List<Book>) request.getAttribute("books");
                            for (int i = 0; i < books.size(); i++) {
                        %>
                        <tr>
                            <td><%=books.get(i).getTitle()%>
                            </td>
                            <td><%=books.get(i).getAuthor()%>
                            </td>
                            <td><%=books.get(i).getPublishing()%>
                            </td>
                            <td><%=books.get(i).getType()%>
                            </td>
                            <td><a class="a-remove btn" href="javascript:;"
                                   borrowid="<%=buys.get(i).getBookid()%>">移除书籍</a></td>
                        </tr>
                        <%
                            }
                        %>


                    </table>
                </div>
            </li>

            <%--下载我的书籍--%>
            <li><p>下载书籍</p>
                <div class="show-right">
                    <a href="/downexcel.do" class="a-addmoney btn btn-success btn-lg">下载我的书单</a>
                </div>
            </li>

            <%--我的相册--%>
            <li id="li-show-photo" class="my-photo"><p>我的相册</p>
                <div class="show-right div-photo">
                    <div class=" container-fluid">
                        <form action="/upload.do?page=1" class="form-inline" method="post"
                              enctype="multipart/form-data">
                            <div class="form-group">
                                <label class="sr-only" for="file">请选择要上传的图片:</label>
                                <input type="file" class="form-control" id="file" name="file">
                            </div>
                            <button type="submit" class="btn btn-default">点击上传</button>
                            <a id="a-remove-picture" href="javascript:;" class="btn btn-default">删除照片</a>
                        </form>
                        <hr>
                        <p>我的照片:</p>
                        <hr>
                        <div class="container-fluid " id="div-show-photos">
                            <c:forEach var="p" items="${requestScope.pictures}" varStatus="pictures">
                                <div class="div-pictures">
                                    <a class="remove-picture btn btn-default" href="javascript:;"
                                       path="${p.photo}">X</a>
                                    <a href="/getpicture.img?photo=${p.photo}" target="_blank">
                                        <img src="/getpicture.img?photo=${p.photo}">
                                    </a>
                                </div>
                            </c:forEach>
                        </div>
                    </div>
                </div>
            </li>
            <%--我的资金--%>
            <li><p>我的资金</p>
                <div class="show-right">
                    <h2>我的可用资金为:${requestScope.zone.money} 元</h2>
                    <a href="https://www.alipay.com/" target="_blank"
                       class="a-addmoney btn btn-success btn-lg">点击充值</a>
                </div>
            </li>

            <%--安全退出--%>
            <li><p>安全退出</p>
                <div class="show-right">
                    <a href="/out.do" class="a-out btn btn-lg btn-success">安全退出</a>
                    <p>点击将删除你的本地cookie,下次将不再自动登录!</p>
                </div>
            </li>
        </ul>
    </div>
</div>
<script type="text/mytemplates" id="script-showphoto">
    {{#each data}}
    <div class="div-pictures">
        <a class="remove-picture btn btn-default" href="javascript:;"
           path="">X</a>
        <a href="/getpicture.img?photo=${p.photo}" target="_blank">
            <img src="/getpicture.img?photo=${p.photo}">
        </a>
    </div>
    {{/each}}
</script>

<script>
    $(function () {
        $("#a-remove-picture").click(function () {
            $(".remove-picture").toggle();
        });

        function showPhoto() {
            $.ajax({
                url: "/userpictures.do",
                type: "post",
                data: {"userid":${sessionScope.user.id}},
                beforeSend: function () {
                    $("#div-show-photos").text("数据正在加载中....")
                },
                success: function () {

                },
                error: function () {

                }
            })
        }

        $(".remove-picture").click(function () {
            if (confirm("你确定要删除吗?")) {

                window.location.href = "/delpicture.do?photo=" + $(this).attr("path");
            }
        });

        $(".nav-left li").click(function () {
            $(".nav-left li").attr("class", "");
            $(this).attr("class", "addactive");
        });

        $(".a-remove").click(function () {
            var borrowid = $(this).attr("borrowid");
            if (confirm("你确定要删除吗?")) {
                window.location.href = "/user/delbuy.do?bookid=" + borrowid;
            }
        })

        <c:if test="${param.photo == '1'}">
        function addClass() {
            $(".nav-left li").attr("class", "");
            $("#li-show-photo").attr("class", "addactive");
        };
        addClass();
        </c:if>
    })
</script>
</body>
</html>
