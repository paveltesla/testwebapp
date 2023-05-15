<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ attribute name="usersPages" required="true" %>
<%@ attribute name="editPass" required="true" %>
<%@ attribute name="home" required="true" %>
<%@attribute name="list" required="true" %>
<div class="header">
    <h1 class="header-title">My Project</h1>
    <c:if test="${usersPages}">
        <div class="header-links">
            <c:if test="${list}">
                <c:if test="${role == 'ADMIN'}">
                    <a href="/list.jhtml">Users List</a>
                </c:if>
            </c:if>
            <c:if test="${editPass}">
                <a href="/editPass.jhtml">Edit password</a>
            </c:if>
            <c:if test="${home}">
                <a href="/auth.jhtml">Home Page</a>
            </c:if>
            <a href="/exit">Exit</a>
        </div>
    </c:if>
</div>
