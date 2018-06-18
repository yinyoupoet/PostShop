<%--
  Created by IntelliJ IDEA.
  User: hasee
  Date: 2018-06-16
  Time: 21:27
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" isErrorPage="true" %>
<html>
<head>
    <title>/(ㄒoㄒ)/~~页面找不到啦</title>
    <link rel="stylesheet" type="text/css" href="<%= request.getContextPath()%>/css/error.css">
</head>

<body>
<a href="javascript:history.go(-1);"><h2 style="color: #f1fbf2">&lt;&lt;&lt;页面找不到啦...点击返回</h2></a>

<div class="fof">
    <canvas></canvas>
</div>

<script  src="<%= request.getContextPath()%>/js//error.js"></script>
</body>
</html>
