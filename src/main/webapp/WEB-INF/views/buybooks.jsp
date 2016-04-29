<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/18
  Time: 10:34
  To change this template use File | Settings | File Templates.
--%>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!doctype html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>购买书籍</title>
    <link rel="stylesheet" href="/Static/css/bootstrap.min.css">
    <link rel="stylesheet" href="/Static/css/bootstrap-theme.min.css">
    <script src="/Static/js/jquery-1.12.2.min.js"></script>
    <script src="/Static/js/bootstrap.min.js"></script>
    <script src="/Static/js/jquery.validate.js"></script>
    <style>
        .th-buy-paymoney span {
            margin: 0 13px;
        }

        .div-pay-money {
            position: absolute;
            top: 0;
            left: 0;
            width: 100%;
            height: 100%;
            background-color: rgba(0, 0, 0, 0.12);
            display: none;
        }

        .pay-momey-small {
            position: absolute;
            top: 20%;
            width: 500px;
            height: 250px;
            border: 1px solid #c1c1c1;
            background-color: rgba(255, 255, 255, 0.93);
            left: 50%;
            margin-left: -250px;
            border-radius: 10px;
        }

        #form-pay-money {
            padding: 25px;
        }
    </style>
</head>
<body>
<%@include file="public.jsp" %>

<div class="container">
    <h4><strong>你要购买的书籍有:</strong></h4>
    <table class="table">
        <tr>
            <th>书名</th>
            <th>作者</th>
            <th>出版社</th>
            <th>价钱</th>
            <th>选择</th>
        </tr>

        <c:forEach items="${requestScope.books}" var="book">
            <tr class="tr-buy-book">
                <td>${book.title}</td>
                <td>${book.author}</td>
                <td>${book.publishing}</td>
                <td class="td-buy-book-price">${book.price}</td>
                <td>
                    <input class="input-onoff" checked type="checkbox" value="${book.id}" index="${book.price}">
                </td>
            </tr>

        </c:forEach>

    </table>

    <div style="border:1px solid black"></div>
    <table class="table pull-right" style="width:60%">
        <tr>
            <th class="th-buy-paymoney">
                <span>总共有:</span>
                <span class="span-booknum" style="font-size: 20px;"></span>
                <span>本书</span>
            </th>

            <th class="th-buy-paymoney">
                <span>应付: &nbsp;</span>
                <span class="span-bookprice" style="font-size: 20px;"></span>
                <span>&nbsp;&nbsp;元</span>
            </th>

            <th>
                <button id="btn-show-pay-div" class="btn btn-success pull-right">点击付款</button>
            </th>
        </tr>
    </table>
</div>

<%--付款div--%>
<div class="div-pay-money">
    <div class="pay-momey-small">
        <div class="div-balance">
            <h3 style="margin:0 0 0 30px; padding:30px 0 0 0">
                <span>你的可用余额为:</span>
                <span class="span-find-balance" style="font-size:20px;"></span>
            </h3>
        </div>
        <form id="form-pay-money">
            <div><h3>请输入密码完成付款:</h3></div>
            <input id="input-formprice" type="hidden" name="price">
            <input id="input-formids" type="hidden" name="ids">
            <input type="password" class="form-control" name="password" placeholder="请输入密码完成付款..."> <br>

            <div class="pull-right">
                <a id="a-back-choose-book" href="javascript:;" class="btn btn-default ">取消付款</a>
                <a id="a-shure-pay-money" href="javascript:;" class="btn btn-success ">确定付款</a>
            </div>
        </form>
    </div>
</div>


<script>
    $(function () {

        var oBooks = document.getElementsByClassName("tr-buy-book");

        $(".span-booknum").text(oBooks.length);

        $(".span-bookprice").text(price());

        var allPrice = 0;
        //计算总钱数
        function price() {
            var oprice = document.getElementsByClassName("td-buy-book-price");
            var num = 0;
            for (var i = 0; i < oprice.length; i++) {
                num += parseInt(oprice[i].innerHTML);
            }
            return num;
        }

        //动态计算当前应付多少钱
        $(".input-onoff").click(function () {
            var allPrice = parseInt($(".span-bookprice").html());
            var booknum = parseInt($(".span-booknum").html());
            var thisPrice = $(this).attr("index");

            if (this.checked) {
                allPrice = allPrice + parseInt(thisPrice);
                booknum++;
            } else {
                allPrice = allPrice - parseInt(thisPrice);
                booknum--;
            }

            $(".span-bookprice").text(allPrice);
            $(".span-booknum").text(booknum);
        });

        //点击付钱后的操作
        $("#btn-show-pay-div").click(function () {
            var obookids = "";
            var oChecks = document.getElementsByClassName("input-onoff");
            for (var i = 0; i < oChecks.length; i++) {
                if (oChecks[i].checked) {
                    obookids += "," + $(oChecks[i]).val();
                }
            }

            $.ajax({
                url: "/user/userfindbalance.do",
                type: "post",
                success: function (json) {
                    if (json.status == "error") {
                        alert("付款失败!服务器异常,请稍后再试!");
                    } else {
                        $(".div-pay-money").show();
                        $("#input-formprice").val($(".span-bookprice").text());
                        $(".span-find-balance").text(json.money);
                        $("#input-formids").val(obookids);
                    }
                },
                error: function () {
                    alert("付款失败!服务器异常,请稍后再试!")
                }
            });
        });

        //支付表单验证
        $("#form-pay-money").validate({
            errorClass: "text-error",
            erroElement: "span",
            reule: {
                password: {
                    required: true
                }
            },
            messages: {
                password: {
                    required: "请输入密码"
                }
            },
            submitHandler: function (form) {
                $.ajax({
                    url: "/user/paymoney.do",
                    type: "post",
                    data: $(form).serialize(),
                    beforeSend: function () {
                        $("#a-shure-pay-money").attr("disabled", "disabled")
                        $("#a-shure-pay-money").text("付款中....")
                    },
                    success: function (json) {
                        if (json.status == "error") {
                            alert(json.errorMessage)
                        } else {
                            $("#a-shure-pay-money").text("购买成功,正在进入个人空间");
                            setTimeout(function () {
                                window.location.href = "/userzone.do";
                            },2000);
                        }
                    },
                    error: function () {
                        alert("参数错误!购买失败!")
                    },
                    complete: function () {
                        $("#a-shure-pay-money").removeAttr("disabled");
                        $("#a-shure-pay-money").text("确定付款")
                    }
                })
            }
        });

        //返回重新选择书籍
        $("#a-back-choose-book").click(function () {
            $(".div-pay-money").hide();
        });
        //点击确认支付
        $("#a-shure-pay-money").click(function () {
            var balance = parseInt($(".span-find-balance").text());
            var price = parseInt($(".span-bookprice").text());

            if (balance < price) {
                alert("付款失败,余额不足,请到个人中心充值!")
            } else {
                $("#form-pay-money").submit();
            }
        })
    })
</script>
</body>
</html>
