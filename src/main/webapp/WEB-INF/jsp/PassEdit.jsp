<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 31.03.2023
  Time: 15:31
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<html>
<head>
    <style>
        <%@include file='../../CSS/style.css' %>
    </style>
    <title>Change Pass Page</title>
</head>
<body>
<div class="wrapper">
    <t:header usersPages="true" list="false" editPass="false" home="true"/>
    <div class="main">
        <div class="content">
            <h2 class="title"><span>Change Password</span></h2>
            <form method="post" action="<c:url value="/editPass.jhtml"/>">
                <input type="password" name="oPass" placeholder="Old Password" autocomplete="off" class="input" >
                <input type="password" name="nPass" placeholder="New Password" autocomplete="off" class="input" >
                <input type="password" name="nPassRep" placeholder="Repeat New Password" autocomplete="off" class="input" >
                <div class="buttons">
                    <button type="submit" class="button"><span>Change Password</span></button>
                </div>
                <p class="message"><c:out value="${message}"/></p>
            </form>
        </div>
    </div>
    <t:footer/>
</div>
</body>
</html>
