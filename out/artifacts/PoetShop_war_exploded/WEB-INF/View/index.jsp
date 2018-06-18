<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-06-14
  Time: 11:34
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
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/nav.css">
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/index-nav.css">

    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/index.css">
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



  <!-- 中间正文部分 -->
  <div class="container-fluid manage-content">
    <div class="manage-main">
      <!-- 左部导航栏 -->
      <div class="manage-left-nav-full panel-group" id="manage-left-nav">

          <c:forEach var="first" items="${requestScope.firstdirectoryMap}">
              <div class="panel panel-default">
                  <a href="#manage-${first.key}" class="manage-left-title panel-title" data-toggle="collapse" data-parent="#manage-left-nav">${first.value.categoryName}</a>

                  <ul id="manage-${first.key}" class="nav nav-list collapse panel-collapse">
                      <c:forEach var="second" items="${requestScope.seconddirectoryMap[first.key]}">
                          <c:url value="/index.action" var="secondUrl">
                              <c:param name="firstId" value="${first.key}"/>
                              <c:param name="secondId" value="${second.id}"/>
                          </c:url>
                          <li class="manage-left-subTitle panel-body"><a href="${secondUrl}">${second.directoryName}</a></li>
                      </c:forEach>
                  </ul>
              </div>
          </c:forEach>


      </div>

      <!-- 右边内容 -->
      <div class="manage-right-content container-fluid">
        <!-- 商品信息头部 -->
        <div class="goods-head">

          <div class="content-right-box gradient-border right-catalog">
              <c:choose>
                  <c:when test="${!empty requestScope.searchTitle}">
                      <li>搜索：${requestScope.searchTitle}</li>
                  </c:when>
                  <c:when test="${!empty requestScope.secondDirectory}">
                      <c:url value="/index.action" var="firstCategory">
                          <c:param name="firstId" value="${requestScope.firstDirectory.id}"/>
                      </c:url>
                      <c:url value="/index.action" var="secondCategory">
                          <c:param name="firstId" value="${requestScope.firstDirectory.id}"/>
                          <c:param name="secondId" value="${requestScope.secondDirectory.id}"/>
                      </c:url>
                      <li><a href="${firstCategory}">${requestScope.firstDirectory.categoryName}</a> &gt;&gt; <a href="${secondCategory}">${requestScope.secondDirectory.directoryName}</a></li>
                  </c:when>
                  <c:when test="${!empty requestScope.firstDirectory}">
                      <c:url value="/index.action" var="firstCategory">
                          <c:param name="firstId" value="${requestScope.firstDirectory.id}"/>
                      </c:url>
                      <li><a href="${firstCategory}">${requestScope.firstDirectory.categoryName}</a></li>
                  </c:when>
                  <c:otherwise>
                      <li>推荐商品</li>
                  </c:otherwise>
              </c:choose>
            <%--<li><a href="#">副食</a> &gt;&gt; <a href="#">炒货</a></li>--%>
          </div>

          <!-- 搜索块 -->
          <div class="content-right-search  content-right-box gradient-border right-search">
            <form class="form-search">
              <input type="text" name="searchTitle" class="input-medium search-query content-search" placeholder="(*/ω＼*)搜索一下吧">
            </form>
          </div>

        </div>

        <!-- 商品详细信息列表 -->
        <div class="goods-list">
            <c:forEach var="good" items="${requestScope.goodsList}">
                <!-- 商品块-样式 1 -->
                <div class="good-item content-right-box gradient-border">

                    <div class="content-left-article-1-img-div">
                        <a href="#" class="content-left-article-1-img-link">
                            <img src="${good.imgPath}" class="content-left-article-1-img">
                        </a>
                    </div>

                    <div class="content-left-article-1-title">
                        <a href="#" class="content-left-link-title">
                            ${good.goodName}
                        </a>
                    </div>



                    <div class="content-left-article-1-state">

                        <ul class="list-inline">
                            <li class="good-item-price">¥ <span>${good.price}元</span></li>

                            <li class="good-item-sales"><i class="fas fa-shopping-cart"></i> <span>${good.saleVolumn}人已购买</span></li>
                        </ul>
                    </div>

                    <div class="good-item-info-btn">
                        <center>
                            <c:url value="/goodinfo.action" var="goodInfo">
                                <c:param name="goodId" value="${good.id}"/>
                            </c:url>
                            <a href="${goodInfo}" class="button button-glow button-rounded button-caution" target="_blank">查看商品详情</a>
                        </center>
                    </div>

                </div>

            </c:forEach>


        </div>
      </div>
    </div>
  </div>


  <script type="text/javascript" src="<%= request.getContextPath()%>/js/jquery-3.1.1.min.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/js/bootstrap.min.js"></script>
  <script type="text/javascript" src="<%= request.getContextPath()%>/js/index.js"></script>
  </body>
</html>
