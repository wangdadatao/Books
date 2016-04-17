<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/3/16
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="/Static/css/bootstrap.min.css">
    <script src="/Static/js/jquery-2.2.1.js"></script>
    <script src="/Static/js/bootstrap.min.js"></script>
    <script src="Static/js/jquery.validate.js"></script>
    <title>登录</title>
    <style>
        body {
            background-image: url("/Static/img/indexbg.jpg");
            margin: 0;
            padding: 0;
            -webkit-background-size: 100% 100%;
            background-size: 100% 100%;
        }

        .login {
            overflow: hidden;
            background-color: rgba(255, 255, 255, 0.44);
            width: 500px;
            position: absolute;
            margin-top: 100px;
            margin-left: -250px;
            left: 50%;
            padding: 0 15px 15px 15px;
            border: 1px solid #d0d2d0;
        }

        .logo {
            height: 70px;
            text-align: center;
            font-size: 25px;
            line-height: 70px;
            font-family: 微软雅黑;
        }

        .row {
            padding: 0 30px;
        }

        #userform > input {
            margin: 30px 0;
        }

        #form-reg > input {
            margin: 15px 0 30px 0;
            height: 35px;
        }

        #adform > input {
            margin: 15px 0 30px 0;
        }

        form span {
            position: absolute;
            color: red;
            margin-top: -58px;
            right: 40px;
        }

        .input-captcha {
            width: 50%;
            display: inline-block;
        }

        .img-captcha {
            margin-left: 20px;
            height: 50px;
        }

        .error-text {
            color: red;
            position: relative;
            right: -70%;
            top: -30px;
        }

        #captcha-error {
            position: absolute;
            margin-top: 16px;
            margin-right: 220px;
        }

    </style>
</head>
<body>

<%--登录框--%>
<div class="login">
    <div class="logo">Books</div>
    <ul class="nav nav-tabs" role="tablist" id="tab-list">
        <li class="active"><a href="#tab-userdenglu" role="tab" data-toggle="tab">学生登录</a></li>
        <li><a href="#tab-zhuce" role="tab" data-toggle="tab">学生注册</a></li>
        <li><a href="#tab-admin" role="tab" data-toggle="tab">管理员登录</a></li>
    </ul>
    <div class="tab-content">
        <%--学生登录--%>
        <div class="tab-pane active" id="tab-userdenglu">
            <div class="row feature">
                <form id="userform" class="form-signin" action="/userlog.do" method="post">
                    <input type="text" id="inputEmail" name="username" class="form-control" placeholder="请输入用户名"
                           autofocus>
                    <input type="password" name="password" class="form-control" placeholder="请输入密码">
                    <div class="checkbox">
                        <label id="input-rememberme">
                            <input  type="checkbox" name="rememberme" value="remember-me"> 记住我
                        </label>
                    </div>
                    <div id="div-show-message" class="container">
                        <h5 id="h5-message"></h5>
                    </div>
                    <button id="btn-log" type="button" class="btn btn-lg btn-primary btn-block btn-zhuce">确认登录</button>
                </form>
            </div>
        </div>
        <%--学生注册--%>
        <div class="tab-pane" id="tab-zhuce">
            <div class="row feature">
                <form action="" id="form-reg" class="form-signin">
                    <input type="text" id="inputname" name="username" class="form-control" placeholder="请输入用户名...">
                    <input type="password" name="password" id="password" class="form-control" placeholder="请输入密码...">
                    <input type="password" name="repassword" class="form-control" placeholder="请再次输入密码...">
                    <div>
                        <input class="input-captcha form-control" type="text" name="captcha" placeholder="请输入验证码...">
                        <a class="a-captcha" href="javascript:;"><img class="img-captcha" src="/captcha.png"></a>
                    </div>
                    <div class="checkbox">
                        <label>
                            <input id="xieyi" type="checkbox" name="rememberme" value="remember-me"> <a
                                href="#">同意注册协议 </a>
                        </label>
                    </div>
                    <button disabled="true" id="btn-reg" class="btn btn-lg btn-primary btn-block ">
                        提交注册
                    </button>
                </form>
            </div>
        </div>
        <%--管理员登录--%>
        <div class="tab-pane" id="tab-admin">
            <div class="row feature">
                <form id="adform" class="form-signin" action="/login.do" method="post">
                    <input type="text" id="ad-input" name="username" class="form-control" placeholder="请输入用户名..."
                           required autofocus>
                    <input type="password" name="password" class="form-control" placeholder="请输入密码..." required>
                    <a id="a-adsubmit" href="javascript:;" class="btn btn-lg btn-primary btn-block a-zhuce">确认登录</a>
                </form>
            </div>
        </div>
    </div>
</div>


<script>
    $(".a-captcha").click(function () {
        $(".img-captcha").attr("src", "/captcha.png?date=" + new Date().getTime());
    });

    //登录
    $("#btn-log").click(function () {
        $("#userform").submit();
    });
    $("#userform").validate({
        errorElement: 'span',
        errorClass: "text-class",
        rules: {
            username: {
                required: true
            },
            password: {
                required: true
            }
        },
        messages: {
            username: {
                required: " 请输入用户名"
            },
            password: {
                required: " 请输入密码"
            }
        },
        submitHandler: function (form) {
            var oLogBtn = $("#btn-log");
            $.ajax({
                url: "/userlog.do",
                type: "post",
                data: $(form).serialize(),
                beforeSend: function () {
                    oLogBtn.text("登录中...");
                    oLogBtn.attr("disabled", "disabled");
                },
                success: function (json) {
                    if (json.status == "error") {
                        var errorHtml = "<span style='color:red;position: relative; margin:-23px -400px 0 0' class='pull-right'>帐号或密码错误!</span>";
                        $("#input-rememberme").append(errorHtml);

                    } else {
                        window.location.href = "/userhome.do";
                    }
                },
                error: function () {
                    alert("服务器错误,请稍后再试!")
                },
                complete: function () {
                    oLogBtn.text("登录");
                    oLogBtn.removeAttr("disabled");
                }
            })

        }
    });

    //注册
    $("#btn-reg").click(function () {
        $("#form-reg").submit();
    });
    $("#form-reg").validate({
        errorClass: "text-error",
        errorElement: "span",
        rules: {
            username: {
                required: true,
                minlength: "3",
                maxlength: "15",
                remote: "/user/valusername.do"
            },
            password: {
                required: true,
                rangelength: [6, 16]
            },
            repassword: {
                required: true,
                equalTo: "#password"
            },
            captcha: {
                required: true,
                remote: "/user/valcaptcha.do"
            }
        },
        messages: {
            username: {
                required: "请输入用户名",
                rangelength: "用户名长度3~15位",
                remote: "该账号已被使用"
            },
            password: {
                required: "请输入密码",
                ranglength: "密码长度6~15位"
            },
            repassword: {
                required: "请再次输入密码",
                equalsTo: "两次输入内容不相同"
            },
            captcha: {
                required: "请输入验证码",
                remote: "验证码输入错误!"
            }
        },
        submitHandler: function (form) {
            $.post("/userreg.do", $(form).serialize()).done(function (result) {
                if (result.status == "error") {
                    alert("注册失败:" + result.errorMessage)
                } else {
                    $("#btn-reg").attr("disabled", "disabled")
                    $("#btn-reg").text("注册成功,请登录!");
                }
            }).fail(function () {
                alert("服务器异常,请稍后再试!")
            });
        }
    });


    var oCheck = document.getElementById("xieyi");

    $("#xieyi").click(function () {
        if (oCheck.checked) {
            $("#btn-reg").removeAttr("disabled");
        } else {
            $("#btn-reg").attr("disabled", "disabled");
        }
    });
    //提交管理员登录表单
    $("#a-adsubmit").click(function () {
        $("#adform").submit();
    });
</script>
</body>
</html>
