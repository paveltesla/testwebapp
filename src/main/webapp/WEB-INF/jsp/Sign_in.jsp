<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Cvetochek
  Date: 08.08.2022
  Time: 12:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<c:set var="role" value="${['USER','ADMIN']}"/>
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
  <t:header usersPages="true" list="false" editPass="false" home="false"/>
  <div class="main">
    <div class="content">
      <h1 class="title"><span>Registration</span></h1>
      <form method="post" action="/reg.jhtml" autocomplete="off">
        <input class="input" type="text" name="login" placeholder="Login">
        <input class="input" type="password"  name="pass" placeholder="Password">
        <input class="input" type="text" name="name" placeholder="Name">
        <input class="input" type="text" name="age" placeholder="Age">
        <div class="roles_change">
          <select id="role" name="role">
            <c:forEach items="${role}" var="role">
              <option value="${role}">${role}</option>
            </c:forEach>
          </select>
        </div>
        <div class="buttons">
          <button class="button" type="submit"><span>Sign Up</span></button>
        </div>
        <p class="message"><c:out value="${message}"/></p>
      </form>
    </div>
  </div>
  <t:footer/>
</div>
</body>
</html>
