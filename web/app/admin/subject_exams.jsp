<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 07.10.2020
  Time: 11:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ page isELIgnored="false" %>
<fmt:setLocale value="${sessionScope.get(\"language\")}" />
<fmt:setBundle basename="resource"/><html>
<head>
    <link rel="stylesheet" href="/app/css/pagination.css">
</head>
<body>

<h2><fmt:message key="add.subject.exam" /></h2>
<form action="/app/admin/add_subject_exam" accept-charset="UTF-8" method="post">
    <fmt:message key="home.faculty.name" /><input name="name" type="text" required="required"> </p>
    <fmt:message key="faculty.description" /><input type="text" name="description" required="required" ></p>
    <fmt:message key="home.faculty.name" />(UA)<input type="text" name="name_ua" required="required" ></p>
    <fmt:message key="faculty.description" />(UA)<input type="text" name="description_ua" required="required" ></p>
   <p><input type="submit" value="<fmt:message key="button.send" />"></p>
</form>
<br>


<h2><fmt:message key="list.subject.exam" /></h2>

<c:forEach items="${requestScope.get(\"subjectExams\")}" var="subjectExam">
    <tr>
        <td>${subjectExam.name}</td>
        <td>${subjectExam.description}</td>
        <li><form method="post" action="<c:url value="/app/admin/delete_subject_exam?id=${subjectExam.id}"/>"><button type="submit"> <fmt:message key="marks.button.delete" /></button></form></li>
    </tr>
    <br>
</c:forEach>

<br>
<div class="pagination">

    <c:forEach var = "numb" begin = "1" end = "${requestScope.get(\"noOfPages\")}" step="1">
        <c:if test="${numb == requestScope.get(\"pageSubjectExam\")}">
            <a class="active" href="/app/admin/subject_exams?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
        <c:if test="${numb != requestScope.get(\"pageSubjectExam\")}">
            <a href="/app/admin/subject_exams?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
    </c:forEach>
</div>

<br>
<a href="${pageContext.request.contextPath}/app/admin/admin_home.jsp"><fmt:message key="button.return.home.admin" /></a>
<br>
<a href="${pageContext.request.contextPath}/app/home.jsp"><fmt:message key="button.return.home" /></a>
</body>
</html>
