<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 13.04.2021
  Time: 20:11
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<form method="post" action="/Initializer">
    <input type="radio" id="task1" name="task" value="task1">
    <label for="task1">Universities | Students | Faculties</label><br>
    <input type="radio" id="task2" name="task" value="task2">
    <label for="task2">Faculties | Students</label><br>
    <input type="radio" id="task3" name="task" value="task3">
    <label for="task3">Students list</label><br>
    <input type="submit" value="DO"/>
</form>
</body>
</html>
