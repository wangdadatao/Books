<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/3/22
  Time: 12:38
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="a" uri="http://java.sun.com/jsp/jstl/core" %>
<head>
    <title>设置</title>
    <link rel="stylesheet" href="/Static/css/bootstrap.css">
    <script src="/Static/js/jquery-2.2.1.js"></script>
    <script src="/Static/js/bootstrap.min.js"></script>
    <script src="/Static/js/jquery.validate.js"></script>

    <style>
        .wrap-nav {
            background-color: #000;
        }

        .navbar-inverse {
            padding: 0;
            margin: 0;
        }

        .set-nav {
            position: absolute;
            top: 100px;
        }

        .set-nav > ul {
            width: 250px;
            margin: 0;
            padding: 0
        }

        .set-nav > ul > li {
            list-style-type: none;
            height: 70px;
            text-align: center;
            border-bottom: 1px solid #e0e0e0;
        }

        .set-nav > ul > li:last-child {
            border-bottom: none;
        }

        .set-nav > ul > li > a {
            text-decoration: none;
            color: #2f2f2f;
            font-family: 微软雅黑;
            line-height: 70px;
            font-size: 20px;
        }

        .set-nav > ul > li:hover > a {
            color: #ff6aa8;
        }

        .show-set {
            width: 700px;
            position: absolute;
            left: 340px;
            top: 50px;
            display: none;
        }

        .addactive > a {
            color: #ff6aa8;
        }

        .addactive .show-set {
            display: block;
        }

        .sett {
            margin: 0;
            padding: 0;
            margin-left: 40px;
        }

        .sett li {
            height: 80px;
            list-style-type: none;
            font-size: 20px;
            font-family: 微软雅黑;
            text-align: left;
        }

        .sett li .inputt {
            width: 70%;
        }

        .sett li:last-child textarea {
            display: block;
        }

        .btn-lg {
            width: 80%;
        }

        .show-head {
            position: absolute;
            box-shadow: 15px 10px 15px;
            width: 200px;
            height: 200px;
            left: 50%;
            margin-left: -150px;
        }

        .show-head img {
            width: 100%;
            height: 100%;
        }

        .set-pwd .form-group {
            height: 60px;
        }

        .img-circle {
            width: 120px;
            height: 120px;
        }

        .set-other {
            height: 400px;
        }

        .tixing {
            position: relative;
            bottom: -70px;
            text-align: center;
            margin: 0 auto;
        }

        .set-email h2 {
            margin-bottom: 30px;
        }

        .set-email .btn-lg {
            margin-top: 30px;
            width: 60%
        }

        #inputCapcha {
            width: 58%;
            margin-top: 30px;

        }

        .upload-img {
            width: 500px;
            position: absolute;
            left: 50%;
            margin-left: -280px;
            top: 250px;
            padding: 0 60px;
        }

        .upload-img input {
            margin: 30px 0 0 70px;
        }

        .upload-img a {
            margin: 20px 0;
        }

        .text-error {
            color: red;
            font-size: 10px;
            position: relative;
            right: 0;
        }


    </style>

</head>
<body>
<div class="wrap-nav navbar-fixed-top">
    <nav class="navbar navbar-inverse">
        <div class="container">
            <div class="container-fluid">
                <!-- 响应式布局-->
                <div class="navbar-header">
                    <button type="button" class="navbar-toggle collapsed" data-toggle="collapse"
                            data-target="#bs-example-navbar-collapse-1" aria-expanded="false">
                        <span class="sr-only">Toggle navigation</span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                        <span class="icon-bar"></span>
                    </button>
                    <a class="navbar-brand" href="#">图书馆</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="/userhome.do?'">主页 <span class="sr-only">(current)</span></a></li>
                        <li><a href="/userzone.do">个人中心</a></li>
                        <li class="active"><a href="#">设置</a></li>
                    </ul>
                    <form class="navbar-form navbar-left navbar-right" role="search">
                        <div class="form-group">
                            <input type="text" class="form-control" placeholder="搜索:作者/书名/出版社">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
</div>

<%--中间个人设置导航--%>
<div class="container">
    <div class="set-nav">
        <ul>
            <%--基本资料设置--%>
            <li class="addactive"><a href="#">个人资料</a>
                <div class="show-set">
                    <div class="container-fluid">
                        <form id="form-set" method="post" class="form-horizontal" disabled="true">
                            <ul class="sett">

                                <li>
                                    昵称： <input type="text" class="inputt" name="petname"
                                               value="${requestScope.zone.petname}">
                                </li>

                                <li>
                                    年龄： <input type="text" class="inputt" name="age" value="${requestScope.zone.age}">
                                </li>
                                <li>
                                    手机号： <input type="text" class="inputt" name="phonenum"
                                                value="${requestScope.zone.phonenum}">
                                </li>

                                <li>
                                    性别：
                                    <c:choose>
                                        <c:when test="${requestScope.zone.sex == '1'}">
                                            <input type="radio" checked class="radioo" name="sex" value="1">男
                                            <input type="radio" class="radioo" name="sex" value="0">女
                                        </c:when>
                                        <c:otherwise>
                                            <input type="radio" class="radioo" name="sex" value="1">男
                                            <input type="radio" checked class="radioo" name="sex" value="0">女
                                        </c:otherwise>
                                    </c:choose>
                                </li>

                                <li>
                                    <button type="button" id="btn-change-base" class="btn-primary btn-lg btn-b">确定修改
                                    </button>
                                </li>
                                <li>
                                    <span id="span-base-message" class="hide"></span>
                                </li>
                            </ul>
                        </form>
                    </div>

                </div>
            </li>

            <%--头像设置--%>
            <li><a href="#">头像设置</a>
                <div class="show-set">
                    <div class="show-head">
                        <img src="http://7xsaoe.com1.z0.glb.clouddn.com/" alt="头像">
                    </div>
                    <div class="upload-img">
                        <form id="form-upload" action="/upload.do?page=2" method="post" enctype="multipart/form-data">
                            <p>请选择不大于100Kb的图片上传</p>
                            <input id="input-uploadimg" type="file" name="touxiang">
                            <a id="a-uploadimg" href="javascript:;" class="btn btn-success btn-lg">确定上传</a>
                        </form>
                    </div>

                </div>
            </li>

            <%--安全验证--%>
            <li><a href="#">安全验证</a>
                <div class="show-set">
                    <div class="set-email">
                        <form action="/email.e" method="post">
                            <c:choose>
                                <c:when test="${requestScope.zone.email != null}">
                                    <h2>已验证邮箱</h2>
                                    <a class="btn btn-success btn-lg" href="#">更换邮箱</a>
                                </c:when>
                                <a:when test="${requestScope.zone.email == null}">
                                    <h2>尚未验证邮箱</h2>
                                    <form id="form-set-email">
                                        <input id="input-email" type="email" name="email" class="input-lg"
                                               placeholder="请输入邮箱:">
                                        <a id="get-email" class="btn btn-default " href="javascript:;">点击获取邮箱验证码</a>
                                        <br>
                                        <img id="img-email" Style="display: none;">
                                        <input id="inputCapcha" class="input-lg" name="captcha" type="text"
                                               placeholder="请输入验证码:"> <br>
                                        <button class="btn btn-success btn-lg">确定绑定</button>
                                    </form>
                                </a:when>
                            </c:choose>
                        </form>
                    </div>
                </div>
            </li>

            <%--修改密码--%>
            <li><a href="#">修改密码</a>
                <div class="show-set">
                    <div class="set-pwd ">
                        <form class="form-horizontal">
                            <div class="form-group">
                                <label for="inputEmail3" class="col-sm-3 control-label">当前密码:</label>
                                <div class="col-sm-7">
                                    <input type="password" class="form-control" id="inputEmail3" placeholder="请输入当前密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword3" class="col-sm-3 control-label">新密码:</label>
                                <div class="col-sm-7">
                                    <input type="password" class="form-control" id="inputPassword3" placeholder="请输入密码">
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="inputPassword4" class="col-sm-3 control-label">确认密码:</label>
                                <div class="col-sm-7">
                                    <input type="password" class="form-control" id="inputPassword4" placeholder="请输入密码">
                                </div>
                            </div>

                            <div class="form-group">
                                <div class="col-sm-offset-2 col-sm-9">
                                    <button type="submit" class="btn-lg btn-success">确定修改</button>
                                </div>
                            </div>
                        </form>
                    </div>

                </div>
            </li>

            <%--绑定账号--%>
            <li><a href="#">绑定帐号</a>
                <div class="show-set ">
                    <div class="set-other">
                        <div class="row" id="summary-container">
                            <div class="col-md-4">
                                <img class="img-circle" src="/Static/img/weixinlogo.png" alt="chrome">
                                <p>未绑定</p>
                                <p>
                                    <a class="btn btn-success" href="http://weixin.qq.com" target="_blank"
                                       role="button">立即绑定</a>
                                </p>
                            </div>
                            <div class="col-md-4">
                                <img class="img-circle" src="/Static/img/weibologo.png" alt="firefox">
                                <p>未绑定</p>
                                <p>
                                    <a class="btn btn-success" href="http://weibo.com/" target="_blank"
                                       role="button">立即绑定</a>
                                </p>
                            </div>
                            <div class="col-md-3">
                                <img class="img-circle" src="/Static/img/qqlogo.png" alt="safari">
                                <p>未绑定</p>
                                <p><a class="btn btn-success" href="http://im.qq.com" target="_blank"
                                      role="button">立即绑定</a></p>
                            </div>
                        </div>
                        <p class="tixing center-block">绑定第三方帐号，可以直接登录，还可以将内容同步到以下平台，与更多好友分享。</p>
                    </div>
                </div>
            </li>

        </ul>
    </div>
</div>


<script>
    $(function () {
        $("#btn-change-base").click(function () {
            $("#form-set").submit();
        });


        $("#form-set").validate({
            errorClass: "text-error",
//            errorElement: "span",
            rules: {
                petname: {
                    required: true
                },
                age: {
                    required: true,
                    digits: true,
                },
                phonenum: {
                    required: true,
                    rangelength: [11, 11]
                },
                sex: {
                    required: true
                }
            },
            messages: {
                petname: {
                    required: "请输入昵称"
                },
                age: {
                    required: "请输入年龄",
                    digits: "输入格式不对"
                },
                phonenum: {
                    required: "请输入手机号",
                    rangelength: "输入格式不对"
                },
                sex: {
                    required: "请选择性别"
                }
            },
            submitHandler: function (form) {
                $.ajax({
                    url: "/usersetbase.do",
                    type: "post",
                    data: $(form).serialize(),
                    beforeSend: function () {
                        $("#btn-change-base").text("设置中...");
                        $("#btn-change-base").attr("disabled", "disabled");
                    },
                    success: function (json) {
                        if (json.status == "error") {
                            alert(json.errorMessage);
                        } else {
                            alert("资料设置成功!")
                            $("#span-base-message").show();
                            $("#span-base-message").text("资料设置成功!")
                        }
                    },
                    complete: function () {
                        $("#btn-change-base").text("设置");
                        $("#btn-change-base").removeAttr("disabled");
                    }
                })
            }
        });


        $("#a-uploadimg").click(function () {
            if (document.getElementById("input-uploadimg").value.length > 0) {
                $("#form-upload").submit();
            }
        });

        $(".set-nav li").click(function () {
            $(".set-nav li").css("border-left", "none");
            $(this).css("border-left", "3px solid #faa");
            $(".set-nav li").attr("class", "");
            $(this).attr("class", "addactive");
        });

        $("#get-email").click(function () {
            $("#img-email").attr("src", "/email.e?email=" + $("#input-email").val());
        })
    })
</script>
</body>
</html>
