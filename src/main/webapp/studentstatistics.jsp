<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en" xmlns:c="http://www.w3.org/1999/html">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Studentstatistics</title>
    <link rel="stylesheet" href="style.css">
    <%@ include file = "userpage.jsp" %>
</head>
<body>
<div class="statistics">
    <h1>Student Statistics</h1>
    <c:forEach var="s" items="${stats}">

        <p>Question:
            <c:out value="${s.key}"/>
            <c:out value="${s.value}"/>
            %
        <p>
    </c:forEach>
    <p>Summary:
        <c:out value="${summary}"/>
        %
    </p>
</div>
</body>
</html>