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
    <title>Login Page</title>
</head>
<body>
<div class="wrapper">
    <t:header usersPages="false" list="false" editPass="false" home="false"/>
    <div class="main">
        <div class="content">
            <h1 class="title"><span>Welcome</span></h1>
            <form method="post" action="/auth.jhtml" autocomplete="off">
                <label>
                    <input class="input" type="text" name="login" placeholder="Login">
                </label>
                <label>
                    <input class="input" type="password" name="pass" placeholder="Password">
                </label>
                <div class="buttons">
                    <button class="button" type="submit"><span>Log In</span></button>
                    <a href="<c:url value="/reg.jhtml"/>" class="link"><span>Sign Up</span></a>
                </div>
            </form>
            <p class="message"><c:out value="${message}"/></p>
        </div>
    </div>
    <t:footer/>
</div>

</body>
</html>
