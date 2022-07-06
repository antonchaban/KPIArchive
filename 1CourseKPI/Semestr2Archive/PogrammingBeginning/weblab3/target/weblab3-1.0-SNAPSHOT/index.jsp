<%@ page import="java.util.Map" %>
<%@ page import="java.util.ArrayList" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
    <style>
        table, th, td {
            border: 1px solid #000000;
        }
    </style>
</head>
<body>
<h1>Введите значение:</h1>
<form method="post" action="/calc">
    <div class="parameters">
        <input type="text" name="aVal" value="${cookie['aVal'].value}" autocomplete="off" required placeholder="a">
        <br/>
        <input type="text" name="bVal" value="${cookie['bVal'].value}" autocomplete="off" required placeholder="b">
        <br/>
        <input type="text" name="cVal" value="${cookie['cVal'].value}" autocomplete="off" required placeholder="c">
        <br/>
        <input type="text" name="dVal" value="${cookie['dVal'].value}" autocomplete="off" required placeholder="d">
        <br/>
    </div>
    <div class="formula">
        <input type="radio" id="form1" name="formula" value="form1" required>
        <a><img src="pic1.png" alt="form 1"></a>
        <label for="form1">Formula 1</label><br>
        <input type="radio" id="form2" name="formula" value="form2" required>
        <a><img src="pic2.png" alt="form 1"></a>
        <label for="form2">Formula 2</label><br>
        <input type="radio" id="form3" name="formula" value="form3" required>
        <a><img src="pic3.png" alt="form 1"></a>
        <label for="form3">Formula 3</label><br>
    </div>
    <input type="submit" value="Calculate"/>
</form>

<% if (request.getSession().getAttribute("parameters") != null) {
%>
<table class="data-display">
    <tr>
        <th>a</th>
        <th>b</th>
        <th>c</th>
        <th>d</th>
        <th>formula</th>
        <th>solution</th>
    </tr>

    <%
        ArrayList<Map<String, String>> sesParams = (ArrayList<Map<String, String>>) request.getSession().getAttribute("parameters");
        for (Map<String, String> param:sesParams) {
            out.println("<tr>");
            for (Map.Entry item: param.entrySet()) {
                out.println("<td>" + item.getValue() + "</td>");
            }
            out.println("</tr>");
        }


    %>
</table>
<%} %>
</body>
</html>