<%@page import="java.util.List" %>
<%@ page import="com.datao.entity.Book" %>
<%@ page language="java" contentType="text/html; charset=utf-8"
         pageEncoding="utf-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>图书馆借书系统</title>
    <link rel="stylesheet" href="Static/css/bootstrap.min.css">

    <style type="text/css">
        body {
            overflow: hidden;
            background-image: url("/Static/img/listbg.jpg");
            -webkit-background-size:;
            background-size:100% auto;
            background-color: #e9dbce;
            height: 100%;
        }
        .gengduo{
            margin-top: 8px;
        }

        .delBook {
            display: none
        }

        .upBook, .addBook {
            display: none
        }

        .up-div {
            position: absolute;
            right: -300px;
            top: 80px;
            width: 300px;
            border: 1px solid #c1c1c1;
            padding: 15px;
            background-color: rgba(255, 255, 255, 0.59);
            border-radius: 10px;
        }

        .table th {
            text-align: center;
            background-color: #f1f1f1;
        }

        body > .container {
            background-color: rgba(255, 255, 255, 0.89);
            height: 100%;
        }

        .add-input{
            width:90%;
        }

    </style>

</head>
<body>
<!--头 导航  -->
<nav class="navbar navbar-default navbar-static-top">
    <div class="container">
        <div class="container-fluid">
            <div class="navbar-header">
                <a class="navbar-brand" href="#"> 图书馆管理系统 </a>
            </div>

            <ul class="nav navbar-nav navbar-right">
                <li>
                    <form action="/home.do" method="post" class="navbar-form navbar-left" role="search">
                        <div class="form-group">
                            <input type="text" name="key" class="form-control" placeholder="查询书籍">
                        </div>
                        <button type="submit" class="btn btn-default">查询</button>
                        <a class="btn btn-default" href="/home.do">查看全部</a>
                    </form>
                </li>
                <li class="gengduo">
                    <button class="btn btn-success  dropdown-toggle" data-toggle="dropdown" role="button" aria-haspopup="true"
                            aria-expanded="false">更多操作 <span class="caret"></span></button>
                    <ul class="dropdown-menu">
                        <li><a class="a-add" href="#">添加</a></li>
                        <li><a class="a-del" href="#">删除</a></li>
                        <li><a class="a-up" href="#">修改</a></li>
                        <li role="separator" class="divider"></li>
                        <li><a class="safeout" href="/out.do">安全退出</a></li>
                    </ul>

                </li>
            </ul>

        </div>
    </div>
</nav>

<!--list 展示书籍-->
<div class="container">
    <div class="">
        <table class="table table-hover table-bordered text-center">
            <caption class="caption">书籍目录</caption>
            <tr>
                <th>书号</th>
                <th>书名</th>
                <th>作者</th>
                <th>出版社</th>
                <th>价钱</th>
                <th class="delBook">删除书籍</th>
                <th class="upBook">修改书籍</th>
            </tr>

            <c:forEach var="b" items="${requestScope.books}" varStatus="books">
                <tr>
                    <td>${b.code}</td>
                    <td>${b.title}</td>
                    <td>${b.author}</td>
                    <td>${b.publishing}</td>
                    <td>${b.price}</td>
                    <td class="delBook"><a href="javascript:;"
                                           index="${b.id}" data-id="${b.id}"> 删除</a></td>
                    <td class="upBook"><a href="javascript:;"
                                          data-id="${b.id}"> 修改</a></td>
                </tr>
            </c:forEach>
            <tr class="addBook">
                <td><input class="add-input" type="text" form="myform" name="code"></td>
                <td><input class="add-input" type="text" form="myform" name="title"></td>
                <td><input class="add-input" type="text" form="myform" name="author"></td>
                <td><input class="add-input" type="text" form="myform" name="publishing"></td>
                <td><input class="add-input" type="text" form="myform" name="price"></td>
                <td>
                    <button class="btn  btn-info" form="myform">新增</button>
                </td>
            </tr>
        </table>
        <form action="/addBook.do" id="myform" method="post"></form>
    </div>

</div>


<!--修改书籍div-->
<div class="up-div">
    <div class="ccontainer">
        <form action="/upBook.do" method="post">
            <legend>修改书籍</legend>
            <div class="form-group">
                <label for="yonghuming1">书号:</label> <input type="text"
                                                            class="form-control" name="code" id="yonghuming1">
            </div>
            <div class="form-group">
                <label for="yonghuming2">书名:</label> <input type="text"
                                                            class="form-control" name="title" id="yonghuming2">
            </div>
            <div class="form-group">
                <label for="pwd1">作者:</label> <input type="text"
                                                     class="form-control" id="pwd1" name="author">
            </div>
            <div class="form-group">
                <label for="pwd2">出版社:</label> <input type="text"
                                                      class="form-control" id="pwd2" name="publishing">
            </div>
            <div class="form-group">
                <label for="pwd">价钱:</label> <input type="text"
                                                     class="form-control" id="pwd" name="price">
            </div>

            <input id="inputId" type="hidden" class="form-control" id="pwd4"
                   name="id">

            <button type="submit" class="btn btn-default">确定修改</button>
            <a id='close-div' href="javascript:;" class="btn btn-default">关闭</a>
        </form>
    </div>
</div>


<script type="text/javascript" src="/Static/js/jquery-2.2.1.js"></script>
<script type="text/javascript" src="/Static/js/bootstrap.min.js"></script>
<script type="text/javascript" src="/Static/js/jquery.validate.js"></script>


<script type="text/javascript">
    $(".a-del").click(function () {
        $(".delBook").toggle();
        $(".upBook").hide();
    })
    $(".a-up").click(function () {
        $(".upBook").toggle();
        $(".delBook").hide();
    })
    $(".a-add").click(function () {
        $(".addBook").toggle();
    })
    $(".delBook>a").click(function () {
        var id = $(this).attr("data-id");
        if (confirm("你确定要删除吗?")) {
            window.location.href = "/delBook.do?id=" + id;
        }
    });
    $(".upBook>a").click(function () {
        var id = $(this).attr("data-id");
        var oTr = this.parentNode.parentNode;
        var oTd = oTr.getElementsByTagName("td");
        var oIdPut = document.getElementById("inputId");

        for (var i = 0; i < oTd.length - 2; i++) {
            var nPut = $('.up-div input')[i];
            nPut.value = oTd[i].innerText;
        }

        oIdPut.value = id;

        $(".up-div").animate({
            right: 0
        })

        containerMove();
    });
    var bool = true;
    function containerMove() {
        if (bool) {
            $("body>.container").animate({
                width: "-=100px",
                marginRight: "+=300"
            })
        }
        bool = false;
    }

    $("#close-div").click(function () {
        bool = true;
        $(".up-div").animate({
            right: "-300px"
        })

        $("body>.container").animate({
            width: "+=100px",
            marginRight: "-=300"
        })

    })
</script>

</body>
</html>