<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 04.10.2020
  Time: 11:01
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
<h3><fmt:message key="home.admin"/></h3>


<li><form method="post" action="/app/admin/all_faculties"><button type="submit"> <fmt:message key="admin.all.faculties" /></button></form></li>
<li><form method="post" action="/app/admin/subject_exams"><button type="submit"> <fmt:message key="admin.all.subjects" /></button></form></li>
<li><form method="post" action="/app/admin/users"><button type="submit"> <fmt:message key="admin.block.user" /></button></form></li>
<li><form method="post" action="/app/admin/generation_statements"><button type="submit"> <fmt:message key="admin.generate.success.users.lists" /></button></form></li>


<br>

<a href="${pageContext.request.contextPath}/app/home.jsp"><fmt:message key="button.return.home" /></a>


</body>
</html>
