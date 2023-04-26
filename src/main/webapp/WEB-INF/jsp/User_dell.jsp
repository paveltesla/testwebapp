<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Cvetochek
  Date: 08.08.2022
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <style>
        <%@include file='../../CSS/style.css' %>
    </style>
    <title>Delete User Page</title>
</head>
<body>
<div class="wrapper">
    <t:header usersPages="true" editPass="false" home="true" list="true"/>
    <div class="main">
        <div class="content">
            <h1 class="title"><span>Delete User ${param.login}?</span></h1>
            <form method="post" action="/dell.jhtml">
                <div class="buttons">
                    <button class="button" type="submit">Yes</button>
                    <a class="button" href="/list.jhtml">No</a>
                </div>
            </form>
        </div>
    </div>
    <t:footer/>
</div>
</body>
</html>