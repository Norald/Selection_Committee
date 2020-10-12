<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 29.09.2020
  Time: 12:42
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

<c:if test="${empty requestScope.get(\"results\")}">
    <h3><fmt:message key="marks.have.no.marks" /></h3>
</c:if>
<c:if test="${not empty requestScope.get(\"results\")}">
 <c:forEach items="${requestScope.get(\"results\")}" var="result">
    <tr>
        <td>${result.subject_exam.name}</td>
        <td>${result.result}</td>
        <td>${result.dateOfExam}</td>
        <li><form method="post" action="<c:url value="/app/mark_del?subjectid=${result.subject_exam.id}"/>"><button type="submit"> <fmt:message key="marks.button.delete" /> </button></form></li>

    </tr>
    <br>
 </c:forEach>
</c:if>


<br>



<form action="/app/add_mark" method="post" name="add_mark_form">
    <select name="marksSelect">
        <c:forEach items="${requestScope.get(\"exams\")}"  var="exam">
            <option value="${exam.id}">${exam.name}</option>
        </c:forEach>
    </select>
    <p> <input type="number" name="mark" required="required" pattern="[0-9]{3}"></p>
    <p><input type="submit" value="<fmt:message key="marks.button.send" />"></p>
</form>


</body>
</html>
