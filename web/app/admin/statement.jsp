<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 08.10.2020
  Time: 15:36
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.get(\"language\")}"/>
<fmt:setBundle basename="resource"/>
<html>
<html>
<head>
</head>
<body>

<c:if test="${empty sessionScope.get(\"results\")}">
    <h3><fmt:message key="have.no.admissions"/></h3>
</c:if>
<c:if test="${not empty sessionScope.get(\"results\")}">
    <c:forEach items="${sessionScope.get(\"results\")}" var="result">
        <tr>
            <td>${result.fullname}</td>
            <td>${result.idn}</td>
            <td>${result.totalExamResult}</td>
            <td>${result.certificatePoint}</td>
            <td>${result.is_approved}</td>
            <td>${result.totalResult}</td>
            <c:if test="${result.is_approved eq 'false'}">
                1234
                <li><form method="post" action="/app/admin/confirm_user_for_statement?idAdmission=${result.id}&operation=approve"><button type="submit"> <fmt:message key="statement.report.user"/></button></form></li>
            </c:if>
            <c:if test="${result.is_approved eq 'true'}">
                1234
                <li><form method="post" action="/app/admin/confirm_user_for_statement?idAdmission=${result.id}&operation=disapprove"><button type="submit"> <fmt:message key="statement.unreport.user"/></button></form></li>
            </c:if>
        </tr>
        <br>
    </c:forEach>
    <br>
    <li>
        <form method="get" action="<c:url value="/app/admin/generate_early_statement"/>">
            <button type="submit"><fmt:message key="generate.statement"/></button>
        </form>
    </li>

    <br>
    <li>
        <form method="get" action="<c:url value="/app/admin/generate_late_statement"/>">
            <button type="submit"><fmt:message key="generate.statement"/>(FINAL)</button>
        </form>
    </li>
</c:if>
</body>
</html>
