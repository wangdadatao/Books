<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/17
  Time: 22:52
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>${requestScope.book.title}</title>
    <link rel="stylesheet" href="/Static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/Static/css/bootstrap-theme.min.css">
    <script src="/Static/js/jquery-1.12.2.min.js"></script>
    <script src="/Static/js/bootstrap.min.js"></script>
    <script src=""></script>

    <style>
        .div-book {
            position: relative;
            height: 400px;
            background-color: #fdfdfd;
            min-width: 900px;
        }

        .div-book-foot {
            position: absolute;
            width: 100%;
            height: 40px;
            bottom: 0;
        }

        .div-show-book {
            margin: 40px;
        }

        .div-show-book img {
            float: left;
            width: 180px;
            height: 250px;
        }

        .ul-show-book {
            float: left;
        }

        .ul-show-book li {
            height: 50px;
            line-height: 50px;
            list-style: none;
            border-bottom: 1px solid rgba(204, 204, 204, 0.47);
        }

        .div-show-score {
            width: 40%;
            height: 250px;
            border-left: 1px solid rgba(204, 204, 204, 0.27);
        }

        .div--foot-status .btn {
            margin: 0 6px;
        }

        .div-star i {
            font-size: 20px;
        }

        #div-star:hover {
            cursor: pointer;
        }


    </style>
</head>
<body>
<%@include file="../public.jsp" %>

<div class="container">

    <div class="div-book">

        <div class="div-title">
            <h3 style="text-indent: 30px; font-family: 微软雅黑;">&nbsp;&nbsp;${requestScope.book.title}</h3>
        </div>
        <div class="div-show-book">
            <img src="http://7xsaoe.com1.z0.glb.clouddn.com/${requestScope.book.cover}"
                 alt="${requestScope.book.title}">
            <ul class="ul-show-book">
                <li>作者:${requestScope.book.author}</li>
                <li>出版社:${requestScope.book.publishing}</li>
                <li>出版年:${requestScope.book.createtime}</li>
                <li>价格:${requestScope.book.price}</li>
                <li>简介:${requestScope.book.why}</li>
            </ul>

            <div class="div-show-score" style="float:right; padding-left:30px">
                <div style="height: 100px; margin-top:50px">
                    <h5>这些人正在读:</h5>
                    <div class="div-reading"></div>
                </div>
                <div>
                    <h5>这些人也想读:</h5>
                    <div class="div-wantread"></div>
                </div>
            </div>
        </div>


        <div class="div-book-foot">
            <div class="container div--foot-status" style="padding-left: 30px">
                <a href="#" class="btn btn-default">想读</a>
                <a href="#" class="btn btn-default">在读</a>
                <a href="#" class="btn btn-default">读过</a>
                <div id="div-star" style="display: inline; padding-left:20px">
                    你的评分:
                    <i class="i-star" abc="1">☆</i>
                    <i class="i-star" abc="2">☆</i>
                    <i class="i-star" abc="3">☆</i>
                    <i class="i-star" abc="4">☆</i>
                    <i class="i-star" abc="5">☆</i>
                </div>

                <div id="div-foot-comment" class="pull-right" >
                    <a href="#" class="btn">添加评论</a>
                    <a href="#" class="btn">添加短评</a>
                </div>
            </div>

        </div>
    </div>

    <div style="width:100%; border:1px solid #909090;margin:8px 0"></div>
</div>

<%--展示评论--%>
<div class="container">
    <div class="container">
        <ul class="nav nav-tabs" role="tablist" id="tab-list">
            <li class="active"><a href="#tab-pingguo" role="tab" data-toggle="tab">最热</a></li>
            <li><a href="#tab-sanxing" role="tab" data-toggle="tab">最新</a></li>
        </ul>
        <div class="tab-content">
            <div class="tab-pane active" id="tab-pingguo">
                <div class="row feature">
                    haha
                </div>
            </div>
            <div class="tab-pane" id="tab-sanxing">
                <div class="row feature">
                    嘿嘿
                </div>
            </div>
        </div>
    </div>
</div>

<%--添加评论--%>
<div class="container">
    <div>

    </div>
</div>
<script>
    //星星评分
    var oStarDiv = document.querySelector("div-star");
    var oStars = document.querySelectorAll("i");
    for (var i = 0; i < oStars.length; i++) {
        oStars[i].index = i;
        oStars[i].onmouseover = function () {
            for (var j = 0; j < oStars.length; j++) {
                $(oStars[j]).css("color", "black");
                $(oStars[j]).html("☆")
            }
            for (var k = 0; k <= this.index; k++) {
                $(oStars[k]).css("color", "yellow");
                $(oStars[k]).html("★")
            }
        }
    }

    $(".i-star").click(function () {
        alert($(this).attr("abc"))
    })


</script>

</body>
</html>
