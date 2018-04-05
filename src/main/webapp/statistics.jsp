<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Statistics</title>
    <link rel="stylesheet" href="style.css">
    <%@ include file = "userpage.jsp" %>
</head>
<body>
<div class="statistics">
    <h1>Statistics</h1>
    <c:forEach var="s" items="${stats}">

        <p>Student Name<a href="statistics?email=<c:out value='${s.key.eMail}'/>">
            <c:out value="${s.key.name}"/>
        </a>
            <c:if test="${not empty s.value}">
                <c:out value="${s.value}"/>
                %
            </c:if>
        <p>

    </c:forEach>
</div>
</body>
</html>