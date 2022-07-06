<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 14.04.2021
  Time: 23:06
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Title</title>
</head>
<body>
<table border = "1" width = "100%">
    <tr>
        <th>Faculty</th>
        <th>Students</th>
    </tr>

    <c:forEach var = "faculty" items = "${requestScope.faculties}">
    <tr>
        <td> <c:out value = "${faculty.name}"/></td>
        <td> <c:out value = "${faculty.countStudents()}"/></td>
    </tr>
    </c:forEach>
<a href="index.jsp"> HOME</a>
</body>
</html>
