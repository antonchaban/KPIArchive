<%@ page import="com.example.webproga2.FormThree" %><%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 23.03.2021
  Time: 14:54
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<h1>Введите числа:</h1>
<img src="pic3.png" alt="form 3">
<form action="${pageContext.request.contextPath}/form3" class="form" method="post">
    a = <input type="text" name="a3Val" value="${cookie['a3Val'].value}" autocomplete="off" required> <br/>
    b = <input type="text" name="b3Val" value="${cookie['b3Val'].value}" autocomplete="off" required> <br/>
    c = <input type="text" name="c3Val" value="${cookie['c3Val'].value}" autocomplete="off" required> <br/>
    d = <input type="text" name="d3Val" value="${cookie['d3Val'].value}" autocomplete="off" required> <br/>
    <input type="submit" value="Calculate"/>
</form>
<a href="index.html"><h2>HOME</h2></a>
</body>
</html>
