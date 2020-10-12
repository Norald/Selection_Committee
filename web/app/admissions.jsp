<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 02.10.2020
  Time: 20:33
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
    <title>Title</title>
    <%@include file="/app/jspf/scriptsBootstrap.jspf" %>

</head>
<body>
<%@include file="/app/jspf/navbar.jspf" %>

<c:if test="${empty sessionScope.get(\"admissions map\")}">
    <h3><fmt:message key="home.have.no.admissions" /></h3>
</c:if>
<c:if test="${not empty sessionScope.get(\"admissions map\")}">
    <h3><fmt:message key="home.your.admissions" /></h3>
    <c:forEach var="admission" items="${sessionScope.get(\"admissions map\")}">
        <h4><fmt:message key="home.faculty.name" /><c:out value="${admission.key}"/> <fmt:message key="home.date.admission" /> <c:out value="${admission.value}"/></h4>
        <li><form method="post" action="<c:url value="/app/admission_del"/>">
            <input type="hidden" name="faculty_name" value="${admission.key}"/>
            <button type="submit"> <fmt:message key="home.button.delete" /> </button></form></li>
        <br>
    </c:forEach>
</c:if>

</body>
</html>
