<%--
  Created by IntelliJ IDEA.
  User: anton
  Date: 20.11.2021
  Time: 21:01
  To change this template use File | Settings | File Templates.
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>

<head>
    <title>Lab1</title>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="${pageContext.request.contextPath}/styles/style.css">
</head>

<body>
<header>
    <span class="innerHeader">
            <h3 class="inner">It is an ancient honor, sir. Epos, olla, et rector.  It is an ancient honor, sir. Epos, olla, et rector.It is an ancient honor, sir. Epos, olla, et rector.</h3>
    </span>
    <p class="footerText">Lorem ipsum dolor sit amet consectetur adipisicing elit. Sint, corrupti? Pol, a bene
        acipenser. Passion, amnesty,
        and endurance. Lorem ipsum, dolor sit amet consectetur adipisicing elit. Facere quaerat voluptate porro
        laudantium omnis
        fuga enim dolorem, nulla quisquam accusamus explicabo sit culpa molestias non autem dolor dolore, laborum
        ratione Lorem ipsum dolor sit amet consectetur adipisicing elit.</p>
</header>
<div class="container">
    <nav class="leftBar" id="leftBar">

        <br>
        <div class="field" id="numListBlock-1">
            <ol id="numList1"></ol>
            <form id="numListForm" onsubmit="return false;">
                <input type="text"
                       value="Lorem ipsum dolor sit amet consectetur adipisicing elit. Sint, corrupti? Pol, a bene acipenser.">
                <p id="log" class="hidden"></p>
                <button onclick="addelement()">Add</button>
                <br>
                <button id="numListSave-1" onclick="saveAll()">Save</button>
            </form>
        </div>
    </nav>
    <div class="main" id="main">
        <c:if test="${!user.isEditor()}">
            <div class="select-action">
                <form action="whoAmI" method="post">
                    <p>Please select an action:</p>
                    <input type="radio" id="view" name="action" value="As viewer">
                    <label for="view">As viewer</label><br>
                    <input type="radio" id="edit" name="action" value="As editor">
                    <label for="edit">As editor</label><br>
                    <input type="submit" value="Submit">
                </form>
            </div>
        </c:if>
        <c:if test="${user.isEditor()}">
            <form action="newText">
                <label for="notifText">Text to show:</label><br>
                <input type="text" id="notifText" name="notifText"><br><br>
                <input type="submit" value="Submit">
            </form>
            <form action="logout" method="POST">
                <button type="submit">Logout</button>
            </form>

        </c:if>
    </div>
    <div class="lilMain" id="lilMain">
        <c:if test="${isOpened}">
            <div id="notification_div">
                <span><c:out value="${textToShow}"/></span>
            </div>
        </c:if>
        <form action="notBar" method="POST">
            <input type="submit" id="btnControl"/>
            <label class="btn" for="btnControl">Show/Hide Notification Bar</label>
        </form>


    </div>
    <div class="rightBar" id="rightBar" onmouseout="task4()">
        <p>Please select text location:</p>
        <form id="task4form">
            <p><input class="pos2" type="checkbox" name="pos2" id="pos2" value="pos2"> pos2</p>
            <p><input class="pos4" type="checkbox" name="pos4" id="pos4" value="pos4"> pos4</p>
            <p><input class="pos5" type="checkbox" name="pos5" id="pos5" value="pos5"> pos5</p>
            <input type="radio" id="right" value="Right" name="position">
            <label for="right">Right</label><br>
            <input type="radio" id="center" value="Center" name="position">
            <label for="center">Center</label><br>
        </form>

    </div>
    <div class="lilRight" id="lilRight">LILRIGHT TEXTLorem ipsum dolor sit amet consectetur, adipisicing elit. Sequi
        optio error minima
        tenetur, deserunt eaque explicabo aut tempore cupiditate consequuntur totam officiis facilis! Reprehenderit
        quis corrupti quia, libero excepturi est.
    </div>
</div>
<footer>
    <p class="footerText">Lorem ipsum dolor sit amet consectetur adipisicing elit. Sint, corrupti? Pol, a bene
        acipenser. Passion, amnesty,
        and endurance. Lorem ipsum dolor sit amet consectetur, adipisicing elit. Nostrum impedit dolorum eos laudantium
        tenetur
        blanditiis nihil ipsum earum quas, quaerat mollitia corporis porro enim eligendi numquam, dolores aliquid
        tempore tempor Lorem ipsum dolor sit amet consectetur adipisicing elit. Nemo ipsa molestiae obcaecati
        repellat dolorum. Sunt laboriosam odit rerum magnam quisquam nesciunt beatae nihil rem excepturi. Ad
        explicabo soluta voluptate ratione!</p>
    <span class="innerFooter">
            <h3 class="inner">It is an ancient honor, sir. Epos, olla, et rector.</h3>
        </span>
</footer>
<script src='${pageContext.request.contextPath}/scripts/file.js'></script>
</body>

</html>
