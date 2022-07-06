<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 14.04.2021
  Time: 17:49
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="sql" uri="http://java.sun.com/jsp/jstl/sql" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<sql:setDataSource driver="com.mysql.jdbc.Driver" url="jdbc:mysql://localhost:3306/lab4db" user="root" password="admin" var="ds"/>
<sql:query dataSource="${ds}" var="result">
    SELECT * FROM students;
</sql:query>

<table border = "1" width = "100%">
    <tr>
        <th>ID</th>
        <th>Name</th>
        <th>MB Idx</th>
        <th>GPA</th>
        <th>Faculty</th>
        <th>University</th>
    </tr>

    <c:forEach var = "row" items = "${result.rows}">
        <tr>
            <td> <c:out value = "${row.id}"/></td>
            <td> <c:out value = "${row.name}"/></td>
            <td> <c:out value = "${row.markbookidx}"/></td>
            <td> <c:out value = "${row.gpa}"/></td>
            <td> <c:out value = "${row.faculty}"/></td>
            <td> <c:out value = "${row.university}"/></td>
        </tr>
    </c:forEach>
</table>
</body>
</html>
