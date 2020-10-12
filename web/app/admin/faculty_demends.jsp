<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 06.10.2020
  Time: 12:50
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
</head>
<body>
<c:set var="faculty" value="${requestScope.get(\"faculty\")}"/>
<tr>
    <td>${faculty.name}</td>
    <td>${faculty.budgetAmount}</td>
    <td>${faculty.totalAmount}</td>
    <td>${faculty.description}</td>
</tr>

<br>
<h2><fmt:message key="add.demends.to.faculty" /></h2>

<form action="/app/admin/faculty_demend" method="post">
    <select name="demendSelect">
        <c:forEach items="${requestScope.get(\"examAvailableList\")}"  var="examAv">
            <option value="${examAv.id}">${examAv.name}</option>
        </c:forEach>
    </select>
    <input type="hidden" name="idFaculty" value="${faculty.id}"/>
    <p><input type="submit" value="<fmt:message key="button.send" />"></p>
</form>

<br>

<c:forEach items="${requestScope.get(\"examList\")}" var="examList">
    <tr>
        <td>${examList.name}</td>
        <td>${examList.description}</td>
        <li><form method="post" action="<c:url value="/app/admin/delete_demend?idFaculty=${faculty.id}&idExam=${examList.id}"/>"><button type="submit"> <fmt:message key="marks.button.delete" /></button></form></li>
    </tr>
    <br>
</c:forEach>

<br>
<a href="${pageContext.request.contextPath}/app/admin/admin_home.jsp"><fmt:message key="button.return.home.admin" /></a>
<br>
<a href="${pageContext.request.contextPath}/app/home.jsp"><fmt:message key="button.return.home" /></a>
</body>
</html>
