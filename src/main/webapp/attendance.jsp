<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance</title>
</head>
<body>
<h1>Attendance</h1>
<a href="userpage">Go back</a>
    <form action="attendance" method = "get">
        <c:forEach var = "s" items = "${students}">
            <input type="checkbox" name="${s.eMail}" value="true">${s.name}<br>
        </c:forEach>
        <input type = "submit" value = "Submit" width = 6em;>
    </form>
</body>
</html>