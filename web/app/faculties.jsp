<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 27.09.2020
  Time: 23:37
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
    <link rel="stylesheet" href="/app/css/pagination.css">
    <%@include file="/app/jspf/scriptsBootstrap.jspf" %>

</head>
<body>
<%@include file="/app/jspf/navbar.jspf" %>


<h2><fmt:message key="faculties.list" /></h2>

<h3>ORDER BY</h3>
<%--sort by A - Z by default--%>
<%--<c:set var="sort" value="${not empty sessionScope.get(\"sort\") ? sessionScope.get(\"sort\") : not empty sessionScope.get(\"sort\") ? sessionScope.get(\"sort\") : 'sortAZ'}" scope="session" />--%>

<form action="/app/faculties" method="post" name="check_sort">
        <c:if test="${sessionScope.get(\"sort\")=='sortAZ'}">
            <input type="radio" value="sortAZ" name="sort" checked="checked"> <fmt:message key="faculties.sort.az" /> <br />
            <input type="radio" value="sortZA" name="sort">  <fmt:message key="faculties.sort.za"/> <br />
            <input type="radio" value="sortBudget" name="sort"> <fmt:message key="faculties.sort.budget"/> <br />
            <input type="radio" value="sortTotal" name="sort"> <fmt:message key="faculties.sort.total"/>
        </c:if>
        <c:if test="${sessionScope.get(\"sort\")=='sortZA'}">
            <input type="radio" value="sortAZ" name="sort"> <fmt:message key="faculties.sort.az"/> <br />
            <input type="radio" value="sortZA" name="sort" checked="checked"> <fmt:message key="faculties.sort.za"/> <br />
            <input type="radio" value="sortBudget" name="sort"> <fmt:message key="faculties.sort.budget"/> <br />
            <input type="radio" value="sortTotal" name="sort"> <fmt:message key="faculties.sort.total"/>
        </c:if>
        <c:if test="${sessionScope.get(\"sort\")=='sortBudget'}">
            <input type="radio" value="sortAZ" name="sort"> <fmt:message key="faculties.sort.az"/> <br />
            <input type="radio" value="sortZA" name="sort"> <fmt:message key="faculties.sort.za"/> <br />
            <input type="radio" value="sortBudget" name="sort" checked="checked"> <fmt:message key="faculties.sort.budget"/> <br />
            <input type="radio" value="sortTotal" name="sort"> <fmt:message key="faculties.sort.total"/>
        </c:if>
        <c:if test="${sessionScope.get(\"sort\")=='sortTotal'}">
            <input type="radio" value="sortAZ" name="sort" >  <fmt:message key="faculties.sort.az"/> <br />
            <input type="radio" value="sortZA" name="sort"> <fmt:message key="faculties.sort.za"/> <br />
            <input type="radio" value="sortBudget" name="sort"> <fmt:message key="faculties.sort.budget"/> <br />
            <input type="radio" value="sortTotal" name="sort" checked="checked"> <fmt:message key="faculties.sort.total"/>
        </c:if>
    <p><input type="submit" value="<fmt:message key="marks.button.send" />"></p>
</form>


<br>



<c:forEach items="${requestScope.get(\"facultiesList\")}" var="faculty">
    <tr>
        <td>${faculty.name}</td>
        <td>${faculty.budgetAmount}</td>
        <td>${faculty.totalAmount}</td>
        <li><form method="post" action="<c:url value="/app/faculty?id=${faculty.id}"/>"><button type="submit"> <fmt:message key="faculties.button.send.admission" /></button></form></li>
        <%--<td><a href="app/faculty?id=${faculty.id}>Send admission!</a></td>--%>
    </tr>
    <br>
</c:forEach>



<div class="pagination">

    <c:forEach var = "numb" begin = "1" end = "${requestScope.get(\"noOfPages\")}" step="1">
        <c:if test="${numb == requestScope.get(\"pageFaculty\")}">
            <a class="active" href="/app/faculties?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
        <c:if test="${numb != requestScope.get(\"pageFaculty\")}">
            <a href="/app/faculties?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
    </c:forEach>
</div>
<br>
</body>
</html>
