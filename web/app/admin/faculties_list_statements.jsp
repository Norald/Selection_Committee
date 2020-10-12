<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 08.10.2020
  Time: 13:36
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

<h2><fmt:message key="faculties.list" /></h2>

<c:forEach items="${requestScope.get(\"facultiesList\")}" var="faculty">
    <form action="/app/admin/generate_statement" accept-charset="UTF-8" method="post">
        <tr>
            <td>${faculty.name}</td>
            <td>${faculty.budgetAmount}</td>
            <td>${faculty.totalAmount}</td>
        </tr>
        <p><input name="id" type="number" hidden="hidden" value="${faculty.id}" checked="checked"/></p>
        <p><input name="date" type="date" required="required"/></p>
        <p><input type="submit" value="<fmt:message key="button.send" />"></p>
    </form>
    <br>
</c:forEach>



<br>


<div class="pagination">

    <c:forEach var = "numb" begin = "1" end = "${requestScope.get(\"noOfPages\")}" step="1">
        <c:if test="${numb == requestScope.get(\"pageFaculty\")}">
            <a class="active" href="/app/admin/generation_statements?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
        <c:if test="${numb != requestScope.get(\"pageFaculty\")}">
            <a href="/app/admin/generation_statements?page=${numb}"><c:out value= "${numb}"></c:out></a>
        </c:if>
    </c:forEach>
</div>


<br>
<a href="${pageContext.request.contextPath}/app/admin/admin_home.jsp"><fmt:message key="button.return.home.admin" /></a>
<br>
<a href="${pageContext.request.contextPath}/app/home.jsp"><fmt:message key="button.return.home" /></a>


</body>
</html>
