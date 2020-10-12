<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 28.09.2020
  Time: 13:05
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${sessionScope.get(\"language\")}" />
<fmt:setBundle basename="resource"/>

<html>
<head>
    <title>Title</title>
    <%@include file="/app/jspf/scriptsBootstrap.jspf" %>

</head>
<body>
<%@include file="/app/jspf/navbar.jspf" %>




<c:set var="faculty" value="${requestScope.get(\"faculty\")}"/>
     <tr>
    <td>${faculty.name}</td>
    <td>${faculty.budgetAmount}</td>
    <td>${faculty.totalAmount}</td>
    <td>${faculty.description}</td>
    </tr>



<c:choose>
    <c:when test="${requestScope.get(\"able to apply\")==false}">
        <h2><fmt:message key="faculty.cant.admit" /></h2>>
        <br />
    </c:when>
    <c:otherwise>
        <h2><fmt:message key="faculty.can.admit" /></h2>>
        <li><form method="post" action="<c:url value="/app/participate"/>">
            <input type="hidden" name="faculty_id" value="${faculty.id}"/>
            <c:out value= "${faculty.id}"></c:out>
            <button type="submit"><fmt:message key="faculty.button.send.request" /></button>
        </form></li>
        <br />
    </c:otherwise>
</c:choose>


</body>
</html>
