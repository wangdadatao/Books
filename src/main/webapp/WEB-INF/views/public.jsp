<%--
  Created by IntelliJ IDEA.
  User: 海涛
  Date: 2016/4/18
  Time: 11:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<div class="wrap-nav " style="background-color:black;">
    <nav id="nav-big" class="navbar  navbar-inverse">
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
                    <a class="navbar-brand" href="#">BOOKS</a>
                </div>

                <!-- Collect the nav links, forms, and other content for toggling -->
                <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
                    <ul class="nav navbar-nav">
                        <li><a href="/userhome.do">主页 </a></li>
                        <li><a id="a-zone" href="/userzone.do">个人中心</a></li>
                        <li><a href="/userset.do">设置</a></li>
                    </ul>
                    <form action="/userhome.do" method="post" class="navbar-form navbar-left navbar-right" role="search">
                        <div class="form-group">
                            <input name="key" type="text" class="form-control" placeholder="搜索:作者/书名/出版社">
                        </div>
                        <button type="submit" class="btn btn-default">搜索</button>
                    </form>
                </div>
            </div>
        </div>
    </nav>
</div>
