<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/3/19
  Time: 16:01
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <title>个人主页</title>
    <link rel="stylesheet" href="Static/css/bootstrap.min.css">
    <script src="Static/js/jquery-2.2.1.js"></script>
    <script src="Static/js/bootstrap.min.js"></script>
    <script src="/Static/js/jquery.twbsPagination.min.js"></script>

    <%--样式--%>
    <style>
        body {
            margin: 0;
            padding: 0;
            background-image: url("/Static/img/dasuihua.jpg");
        }

        .wrap-nav {
            background-color: #000;
        }

        .navbar {
            margin: 0;
        }

        #nav-book {
            position: fixed;
            top: 100px;
            left: 6%;
        }

        .book-bar {
            background-color: rgba(255, 255, 255, 0.71);
            margin: 0;
            padding: 0;
            width: 90px;
            overflow: hidden;
            border: 1px solid #5c5c5c;
            border-bottom-left-radius: 14px;
            border-top-left-radius: 14px;
            overflow: hidden;
            border-right: none;
        }

        .book-bar > li {
            height: 35px;
            line-height: 35px;
            list-style-type: none;
            text-align: center;
            font-size: 20px;
        }

        .book-bar > li > a {
            color: #0f0f0f;
            text-decoration: none;
        }

        .book-bar > li:hover {
            border: 2px solid #989898;
            height: 40px;
            line-height: 40px;
            box-shadow: 4px 0 15px;
            background-color: #a9a9a9;
        }

        .book-bar > li:hover a {
            color: #fff;
        }

        .book-list {
            width: 75%;
            border: 1px solid #c1c1c1;
            padding-top: 15px;
            position: relative;
            margin: -35px auto 0 auto;
            top: 70px;
            background-color: rgba(255, 255, 255, 0.74);
        }

        .book-list table th, tr {
            text-align: center;
        }

        .table th:nth-child(1) {
            width: 25%;
        }

        .table th:nth-child(2) {
            width: 25%;
        }

        .table th:nth-child(3) {
            width: 25%;
        }

        .table th:nth-child(4) {
            width: 10%;
        }

        .backpack {
            width: 200px;
            position: fixed;
            right: -300px;
            top: 100px;
            border: 1px solid #888888;
            padding: 5px;
            background-color: rgba(255, 255, 255, 0.56);

        }

        .backpack th {
            text-align: center;
        }

        .backpack th:nth-child(1) {
            width: 60%;
        }

        .backpack button {
            width: 68%;
            margin: 0 auto;
        }

        #pages {
            margin: 0;
            padding: 0;
        }

        .nav-foot {
            padding: 20px auto 0 auto;
            margin: 0;
        }

        .nav-foot li {
            margin: 20px 30px 0;
            list-style: none;
            height: 20px;
            float: left;
        }

        .nav-foot > li > a {
            color: #d0d0d0;
        }

        .nav-foot > li > a:hover {
            color: #ffffff;
        }

        .logos {
            display: inline-block;
        }

        .logos img {
            width: 40px;
            height: 40px;
            margin: 10px 30px 0;
            background-color: #fff;
        }
    </style>

</head>
<body>
<%--导航--%>
<%@include file="public.jsp"%>

<%--书籍展示--%>
<div class="container book-list">
    <div class="container-fulid ">
        <table class="table table-bordered table-condensed">
            <tr>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>价格</th>
                <th><a href="#">操作</a></th>
            </tr>
            <c:forEach items="${requestScope.page.items}" var="b">
                <tr>
                    <td><a href="/book/showbook/do?id=${b.id}">${b.title}</a></td>
                    <td>${b.author}</td>
                    <td>${b.publishing}</td>
                    <td>${b.price}</td>
                    <td><a class="a-add btn" href="javascript:;" boolean="true" index="${b.id}">加入书包</a></td>
                </tr>
            </c:forEach>
        </table>
    </div>

    <%--书籍展示--%>
    <div class="pagination" id="pages"></div>
</div>


<div class="div-foot" style="border:1px solid transparent; height:60px; margin-top:100px;background-color:#373737;">

    <div class="container">
        <ul class="nav-foot">
            <li><a href="javascript:;">关于我们</a></li>
            <li><a href="javascript:;">联系我们</a></li>
            <li><a href="javascript:;">加入我们</a></li>
            <li><a href="javascript:;">意见反馈</a></li>
            <li><a href="javascript:;">友情链接</a></li>
            <li><a href="javascript:;">去首页</a></li>
        </ul>
        <div class="logos">
            <a target="_blank" href="http://weibo.com"><img src="/Static/img/weibologo.png"> </a>
            <a target="_blank" href="http://weixin.qq.com"><img src="/Static/img/weixinlogo.png"> </a>
            <a target="_blank" href="http://qq.com"><img src="/Static/img/qqlogo.png"> </a>
        </div>

    </div>
</div>

<%--书包--%>
<div class="backpack">
    <table id="backTable" class="table table-bordered">
        <tr>
            <th>书名</th>
            <th>价格</th>
            <%--<th>移除</th>--%>
        </tr>
    </table>
    <form action="/user/buybooks.do" method="post">
        <input id="input-ids" type="hidden" name="ids">
        <input id="input-price" type="hidden" name="price">
        <button type="btn-buy-submit" class="btn btn-success">确认购买</button>
        <a class="a-close btn btn-default " href="javascript:;">关闭</a>
    </form>
</div>

<script>
    var oFoot = document.getElementsByClassName("div-foot")[0];

    //alert(oFoot.offsetTop);
    if (oFoot.offsetTop < 600) {
        oFoot.style.position = "fixed";
        oFoot.style.width = "100%";
        oFoot.style.bottom = -100 + "px";
        $(".div-foot").animate({
            bottom: 0
        })
    }

    $("#pages").twbsPagination({
        totalPages:${requestScope.page.totalPages},
        visiblePages: 6,
        first: '首页',
        last: '末页',
        prev: '上一页',
        next: '下一页',
        href: "?p={{number}}"
    });


    $(".a-close").click(function () {
        $(".backpack").animate({
            right: "-300px",
        }, 600);
    });

    var oIds = document.getElementById("input-ids");
    $(".a-add").click(function () {
        this.className += " disabled";
        oIds.value += ($(this).attr("index") + ",");

        $(".backpack").animate({
            right: 0
        }, 600);

        var oTr = this.parentNode.parentNode;
        var oTds = oTr.getElementsByTagName("td");

        var nTr = document.createElement("tr");
        var nameTd = document.createElement("td");
        nameTd.innerHTML = oTds[0].innerHTML;

        var priceTd = document.createElement("td");
        priceTd.innerHTML = oTds[3].innerHTML;

        /*var nA = document.createElement("a");
         nA.innerHTML = "移除";
         nA.className = "a-remove";
         nA.index = this.index;
         */
        nTr.appendChild(nameTd);
        nTr.appendChild(priceTd);
//        nTr.appendChild(nA);

        $("#backTable").append(nTr);

    })

</script>
</body>
</html>
