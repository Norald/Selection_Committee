<%--
  User: Влад
  Date: 05.10.2020
  Time: 17:45
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

<h2><fmt:message key="faculty.add" /></h2>
<form action="/app/admin/add_faculty" accept-charset="UTF-8" method="post">
    <fmt:message key="home.faculty.name" /><input name="name" type="text" required="required"> </p>
    <fmt:message key="faculty.description" /><input type="text" name="description" required="required" ></p>
    <fmt:message key="home.faculty.name" />(UA)<input type="text" name="name_ua" required="required" ></p>
    <fmt:message key="faculty.description" />(UA)<input type="text" name="description_ua" required="required" ></p>
    <fmt:message key="faculty.budget.amount" /><input type="number" name="budget_amount" required="required" pattern="[0-9]{3}"></p>
    <fmt:message key="faculty.total.amount" /><input type="number" name="total_amount" required="required" pattern="[0-9]{3}"></p>
    <p><input type="submit" value="<fmt:message key="button.send" />"></p>
</form>
<br>


<h2><fmt:message key="faculties.list" /></h2>

<c:forEach items="${requestScope.get(\"facultiesList\")}" var="faculty">
    <tr>
        <td>${faculty.name}</td>
        <td>${faculty.budgetAmount}</td>
        <td>${faculty.totalAmount}</td>
        <li><form method="post" action="<c:url value="/app/admin/delete_faculty?id=${faculty.id}"/>"><button type="submit"> <fmt:message key="marks.button.delete" /></button></form></li>
        <li><form method="post" action="<c:url value="/app/admin/faculty_admissions?id=${faculty.id}"/>"><button type="submit"> <fmt:message key="button.change.admissions" /></button></form></li>

    <%--<td><a href="app/faculty?id=${faculty.id}>Send admission!</a></td>--%>
    </tr>
    <br>
</c:forEach>



<br>


<div class="pagination">

    <c:forEach var = "numb" begin = "1" end = "${requestScope.get(\"noOfPages\")}" step="1">
        <c:if test="${numb == requestScope.get(\"pageFaculty\")}">
            <a class="active" href="/app/admin/all_faculties?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
        <c:if test="${numb != requestScope.get(\"pageFaculty\")}">
            <a href="/app/admin/all_faculties?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
    </c:forEach>
</div>


<br>
<a href="${pageContext.request.contextPath}/app/admin/admin_home.jsp"><fmt:message key="button.return.home.admin" /></a>
<br>
<a href="${pageContext.request.contextPath}/app/home.jsp"><fmt:message key="button.return.home" /></a>


</body>
</html>
