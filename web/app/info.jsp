<%--
  Created by IntelliJ IDEA.
  User: Влад
  Date: 03.10.2020
  Time: 22:09
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

<h3> <fmt:message key="info.your.personal.info"/></h3>
<c:set var="user" value="${requestScope.get(\"user\")}"/>
<c:set var="details1" value="${requestScope.get(\"details1\")}"/>
<c:set var="details2" value="${requestScope.get(\"details2\")}"/>

<form action="/app/changeinfo" accept-charset="UTF-8" method="post">
    <c:if test="${sessionScope.get(\"language\") == 'uk'}">
        <p><fmt:message key="info.email" />  (Укр)<input name="email" type="email" required="required" value="${user.email}"></p>
        <p><fmt:message key="info.password" />  (Укр)<input type="password" name="pass1" required="required" pattern="[0-9]{5,15}" value="${user.password}" ></p>
        <p><fmt:message key="info.idn" /> (Укр)<input type="number" name="idn" required="required" pattern="[0-9]{10}" value="${user.idn}"></p>
        <p><fmt:message key="info.name" /> (Укр)<input type="text" name="name_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.name}"></p>
        <p><fmt:message key="info.surname" /> (Укр)<input type="text" name="surname_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.surname}"></p>
        <p><fmt:message key="info.patronymic" /> (Укр)<input type="text" name="patronymic_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.patronymic}"></p>
        <p><fmt:message key="info.city" /> (Укр)<input type="text" name="city_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.city}"></p>
        <p><fmt:message key="info.region" /> (Укр)<input type="text" name="region_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.region}"></p>
        <p><fmt:message key="info.school.name" /> (Укр)<input type="text" name="school_name_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.school_name}"></p>
        <p><fmt:message key="info.average.certificate.point" /> <input type="number" name="average_certificate_point" required="required" pattern="[0-9]{3}" value="${details1.average_certificate}"></p>
        <p><fmt:message key="info.name" /> <input type="text" name="name" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.name}"></p>
        <p><fmt:message key="info.surname" /> <input type="text" name="surname" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.surname}"></p>
        <p><fmt:message key="info.patronymic" /> <input type="text" name="patronymic" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.patronymic}"></p>
        <p><fmt:message key="info.city" /> <input type="text" name="city" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.city}"></p>
        <p><fmt:message key="info.region" /> <input type="text" name="region" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.region}"></p>
        <p><fmt:message key="info.school.name" /> <input type="text" name="school_name" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.school_name}"></p>
    </c:if>
    <c:if test="${sessionScope.get(\"language\") == 'en'}">
        <p><fmt:message key="info.email" /> <input name="email" type="email" required="required" value="${user.email}"></p>
        <p><fmt:message key="info.password" /> <input type="password" name="pass1" required="required" pattern="[0-9]{5,15}" value="${user.password}"></p>
        <p><fmt:message key="info.idn" /><input type="number" name="idn" required="required" pattern="[0-9]{10}" value="${user.idn}"></p>
        <p><fmt:message key="info.name" /><input type="text" name="name" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.name}"></p>
        <p><fmt:message key="info.surname" /><input type="text" name="surname" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.surname}"></p>
        <p><fmt:message key="info.patronymic" /><input type="text" name="patronymic" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.patronymic}"></p>
        <p><fmt:message key="info.city" /><input type="text" name="city" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.city}"></p>
        <p><fmt:message key="info.region" /><input type="text" name="region" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.region}"></p>
        <p><fmt:message key="info.school.name" /><input type="text" name="school_name" required="required" pattern="^[A-Za-z0-9]+$" value="${details2.school_name}"></p>
        <p><fmt:message key="info.average.certificate.point" /><input type="number" name="average_certificate_point" required="required" pattern="[0-9]{3}" value="${details2.average_certificate}"></p>
        <p><fmt:message key="info.name" /> (UA)<input type="text" name="name_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.name}"></p>
        <p><fmt:message key="info.surname" /> (UA)<input type="text" name="surname_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.surname}"></p>
        <p><fmt:message key="info.patronymic" /> (UA)<input type="text" name="patronymic_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.patronymic}"></p>
        <p><fmt:message key="info.city" /> (UA)<input type="text" name="city_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.city}"></p>
        <p><fmt:message key="info.region" /> (UA)<input type="text" name="region_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.region}"></p>
        <p><fmt:message key="info.school.name" /> (UA)<input type="text" name="school_name_ua" required="required" pattern="^[А-Яа-яҐЄІЇіїєґ0-9]+$" value="${details1.school_name}"></p>
    </c:if>
    <p><input type="submit" value="<fmt:message key="button.send" />"></p>
</form>

</body>
</html>
