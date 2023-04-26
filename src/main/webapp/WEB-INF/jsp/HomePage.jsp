<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 26.04.2023
  Time: 23:52
  To change this template use File | Settings | File Templates.
--%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <style>
        <%@include file='../../CSS/style.css' %>
    </style>
    <title>Welcome</title>
</head>
<body>
<div class="wrapper">
    <t:header editPass="true" home="false" list="true" usersPages="true"/>
    <div class="main">
        <div class="content">
            <div class="title">
                <div class="title-text">
                    <h1>Hello ${login}!</h1>
                </div>
            </div>
        </div>
    </div>
    <t:footer/>
</div>
<script src="../../JS/effects.js"></script>
</body>
</html>
