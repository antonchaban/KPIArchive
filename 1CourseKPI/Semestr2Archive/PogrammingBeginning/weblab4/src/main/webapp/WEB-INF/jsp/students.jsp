<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 13.04.2021
  Time: 18:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Students</title>
    <style>
        .green{background-color: green}
        .red{background-color: red}
    </style>
</head>
<body>
<table border = "1" width = "100%">
    <tr>
        <th>Name</th>
        <th>MBIdx</th>
        <th>GPA</th>
    </tr>

    <c:forEach var = "students" items = "${requestScope.students}">
    <tr>
        <td> <c:out value = "${students.name}"/></td>
        <td> <c:out value = "${students.markBookIndex}"/></td>
        <td style="${students.GPA < 95 ? 'background-color: red':'background-color: green'}">${students.GPA}</td>
    </tr>
    </c:forEach>
    <a href="index.jsp"> HOME</a>
</body>
</html>
