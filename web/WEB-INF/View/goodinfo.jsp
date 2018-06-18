<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-06-17
  Time: 15:00
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <meta charset="utf-8">
    <!-- https://www.cnblogs.com/baiyii/p/6973437.html  ,使用@media -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0, user-scalable=no">
    <meta http-equiv="X-UA-Compatible" content="IE=Edge, chrome=1">

    <title>吟游小铺</title>

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/bootstrap.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/scroll-style-change.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/fontawesome-all.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/flow-border.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/buttons.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/bootstrap-spinner.css" >
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/zoomify.min.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/index-nav.css">

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/goodinfo.css">
</head>
<body>
<!-- 顶部导航栏 -->
<header>
    <nav class="navbar navbar-fixed-top my-navbar top-nav" role="navigation">
        <div class="container-fluid">
            <div class="navbar-header">

                <!-- 左边 -->
                <div>
                    <a class="navbar-brand" href="/index.action" ><img src="<%= request.getContextPath()%>/imgs/p.png" class=" nav-icon"></a>
                    <a class="navbar-brand" href="/index.action" ><b class="navbar-title" title="YinYouPoet's Blog">吟游小铺</b></a>
                </div>

                <c:choose>
                    <c:when test="${!empty sessionScope.userinfo}">
                        <!-- 右边 -->
                        <!-- 已经登录 -->
                        <div class="isLogin">
                            <a href="#" class="shop-cart" title="购物车"><i class="fas fa-cart-plus"></i></a>
                            <img src="<%= request.getContextPath()%>/imgs/p.png" class="nav-head-img" title="个人信息">
                        </div>
                    </c:when>
                    <c:otherwise>
                        <!-- 没有登录 -->
                        <div class=" notLogin">
                            <a href="/toLoginOrRegister.action" class="navbar-brand">登录/注册</a>
                        </div>
                    </c:otherwise>

                </c:choose>

            </div>

        </div>
    </nav>

    <!-- 已经登录，则鼠标移到图片上显示信息 -->
    <div class="nav-self-info">

        <img src="./imgs/p.png" class="head-info">
        <div style="display: inline;">
            <ul style="display: inline; float: left;" class="info-ul">
                <li><a href="#" class="info-link">${sessionScope.userinfo.userName}</a></li>
                <%--<li><a href="#" class="info-link">编辑资料</a></li>--%>
                <li><a href="/logout.action" class="info-link">登出</a></li>
            </ul>
        </div>
    </div>


</header>

<!-- 正文部分 -->
<div class="content ">
    <div class="container good-info-div">
        <div class="row ">
            <div class="">
                <!-- 左边一块，商品图片 -->
                <div class="col-xs-6">
                    <img src="${requestScope.goodInfo.imgPath}" class="good-img">
                </div>

                <!-- 右边一块，商品详细信息 -->
                <div class="col-xs-6">
                    <div class="good-title">
                        <a href="#" class="good-link-title">
                            ${requestScope.goodInfo.goodName}
                        </a>
                    </div>

                    <span>劲爆价：<h2 class="good-price">${requestScope.goodInfo.price}元</h2></span>
                    <br>
                    <span>总销量：<h3 class="good-sale-volumn">${requestScope.goodInfo.saleVolumn}件</h3></span>
                    <br>

                    <!-- 选择商品数量与购买商品 -->
                    <div class="good-buy">
                        <span style="float: left; margin-top: 7px;margin-right: 20px;">数量：</span>
                        <div tabindex="1" class="input-group spinner good-select good-select-amount" data-trigger="spinner" id="spinner">
                            <input type="text" class="form-control text-center select-amount" value="1" data-rule="poetGoods">
                            <span class="input-group-addon">
								<a href="javascript:;" class="spin-up" data-spin="up"><%--<i class="fa fa-caret-up"></i>--%>+</a>
								<a href="javascript:;" class="spin-down" data-spin="down"><%--<i class="fa fa-caret-down"></i>--%>-</a>
							</span>

                        </div>
                        <br>
                        库存：<span class="good-stock" >${requestScope.goodInfo.stock}</span>件<span class="warning"> &nbsp;&nbsp;&nbsp;(选中的商品数量超过库存)</span>
                        <br><br>
                        <button class="button button-3d button-caution" id="add-to-shop-cart" ><i class="fas fa-shopping-cart"></i> 加入购物车</button>
                    </div>
                </div>
            </div>
        </div>

    </div>
</div>




<script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/bootstrap.min.js"></script>
<script src="<%= request.getContextPath()%>/js/jquery.spinner.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/zoomify.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/index.js"></script>
<script type="text/javascript" src="<%= request.getContextPath()%>/js/goodinfo.js"></script>
</body>
</html>
