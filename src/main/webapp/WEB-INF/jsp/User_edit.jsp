<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: pavel
  Date: 03.04.2023
  Time: 12:34
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<c:set var="role" value="${['USER','ADMIN']}"/>
<html>
<head>
    <style>
        <%@include file='../../CSS/style.css' %>
    </style>
    <title>Edit User Page</title>
</head>
<body>
<div class="wrapper">
    <t:header usersPages="true" list="true" editPass="false" home="true"/>
    <div class="main">
        <div class="content">
            <h1 class="title_edit"><span>Editing User <c:out value="${user.getLogin()}"/></span></h1>
            <form action="/edit.jhtml" method="post">
                <input type="text" name="login" placeholder="Login" autocomplete="off" class="input"
                       value="${user.getLogin()}">
                <input type="text" name="name" placeholder="Name" autocomplete="off" class="input"
                       value="${user.getName()}">
                <input type="text" name="age" placeholder="Age" autocomplete="off" class="input"
                       value="${user.getAge()}">
                <input type="date" name="birthday" class="input" value="${user.getBirthday()}">
                <input type="text" name="salary" placeholder="Salary" autocomplete="off" class="input"
                       value="${user.getSalary()}">
                <div class="select">
                    <select id="role" name="role">
                        <c:forEach items="${role}" var="role">
                            <option value="${role}">${role}</option>
                        </c:forEach>
                    </select>
                </div>
                <div class="buttons">
                    <button type="submit" class="button"><span>Edit User</span></button>
                </div>
                <p class="message"><c:out value="${message}"/></p>
            </form>
        </div>
    </div>
    <t:footer/>
</div>
</body>
</html>
