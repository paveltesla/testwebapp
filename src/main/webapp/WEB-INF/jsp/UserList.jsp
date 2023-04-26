<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="Java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@ taglib prefix="fmt" uri="jakarta.tags.fmt" %>
<html>
<head>
    <style>
        <%@include file='../../CSS/style.css' %>
    </style>
    <title>User List Page</title>
</head>
<body>
<div class="wrapper">
    <t:header usersPages="true" list="false" editPass="false" home="true"/>
    <main class="main">
        <div class="content">
            <h1 class="title"><span>User List</span></h1>
            <table class="table">
                <tr>
                    <th>Login</th>
                    <th>Password</th>
                    <th>Name</th>
                    <th>Surname</th>
                    <th>Patronymic</th>
                    <th>Birthday</th>
                    <th>Role</th>
                    <th>Email</th>
                    <th>Edit User</th>
                    <th>Delete User</th>
                </tr>
                <%--@elvariable id="list" type="java.util.List"--%>
                <c:forEach var="user" items="${list}">
                    <tr>
                        <td><c:out value="${user.getLogin()}"/></td>
                        <td><c:out value="${user.getPass()}"/></td>
                        <td><c:out value="${user.getName()}"/></td>
                        <td><c:out value="${user.getSurname()}"/></td>
                        <td><c:out value="${user.getPatronymic()}"/></td>
                        <td><fmt:formatDate pattern="dd-MM-yyyy" value="${user.getBirthday()}"/></td>
                        <td><c:out value="${user.getRole()}"/></td>
                        <td><c:out value="${user.getEmail()}"/></td>
                        <td style="border: none">
                            <a href="<c:url value="/edit.jhtml?login=${user.getLogin()}"/>" class="link"><span>Edit</span></a>
                        </td>
                        <td style="border: none">
                            <a href="<c:url value="/dell.jhtml?login=${user.getLogin()}"/>" class="link"><span>Delete</span></a>
                        </td>
                    </tr>
                </c:forEach>
            </table>
            <div class="buttons">
                <a class="button" href="<c:url value="/add.jhtml"/>">Add User</a>
            </div>
        </div>
    </main>
    <t:footer/>
</div>
</body>
</html>