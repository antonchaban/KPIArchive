<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 14.04.2021
  Time: 22:39
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border = "1" width = "100%">
    <tr>
        <th>University</th>
        <th>Students</th>
        <th>Faculties</th>
    </tr>

    <c:forEach var = "university" items = "${requestScope.universities}">
    <tr>
        <td> <c:out value = "${university.name}"/></td>
        <td> <c:out value = "${university.countStudents()}"/></td>
        <td> <c:out value = "${university.faculties.size()}"/></td>
    </tr>
    </c:forEach>
<a href="index.jsp"> HOME</a>
</body>
</html>
