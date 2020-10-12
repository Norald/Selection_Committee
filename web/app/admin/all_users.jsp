<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 06.10.2020
  Time: 18:32
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.get(\"language\")}" />
<fmt:setBundle basename="resource"/>
<html>
<head>
    <link rel="stylesheet" href="/app/css/pagination.css">
</head>
<body>
<h2><fmt:message key="users.list" /></h2>

<c:forEach items="${requestScope.get(\"usersList\")}" var="users">
    <tr>
        <fmt:message key="users.email" /> <td>${users.email}</td>
        <fmt:message key="users.idn" /> <td>${users.idn}</td>
        <fmt:message key="users.password" /> <td>${users.password}</td>
        <c:if test="${users.blocked == '0'}">
            <form method="post" action="<c:url value="/app/admin/block_user?id=${users.id}&operation=block"/>"><button type="submit"> <fmt:message key="button.block" /> </button></form>
        </c:if>
        <c:if test="${users.blocked ne '0'}">
            <form method="post" action="<c:url value="/app/admin/block_user?id=${users.id}&operation=unblock"/>"><button type="submit"> <fmt:message key="button.unblock" /> </button></form>
        </c:if>
        <c:if test="${users.user_role_id == '2'}">
            <form method="post" action="<c:url value="/app/admin/user_set_role?id=${users.id}&operation=makeUser"/>"><button type="submit"> <fmt:message key="button.set.user.role" /> </button></form>
        </c:if>
        <c:if test="${users.user_role_id ne '2'}">
            <form method="post" action="<c:url value="/app/admin/user_set_role?id=${users.id}&operation=makeAdmin"/>"><button type="submit"> <fmt:message key="button.set.admin.role" /> </button></form>
        </c:if>
    </tr>
    <br>
</c:forEach>

<br>
<div class="pagination">

    <c:forEach var = "numb" begin = "1" end = "${requestScope.get(\"noOfPages\")}" step="1">
        <c:if test="${numb == requestScope.get(\"pageFaculty\")}">
            <a class="active" href="/app/admin/users?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
        <c:if test="${numb != requestScope.get(\"pageFaculty\")}">
            <a href="/app/admin/users?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
    </c:forEach>
</div>

<br>
<a href="${pageContext.request.contextPath}/app/admin/admin_home.jsp"><fmt:message key="button.return.home.admin" /></a>
<br>
<a href="${pageContext.request.contextPath}/app/home.jsp"><fmt:message key="button.return.home" /></a>
</body>
</html>
