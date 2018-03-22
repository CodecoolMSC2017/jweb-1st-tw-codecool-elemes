<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>elemes</title>
    <link rel="stylesheet" href="style.css">
</head>
<body>
    <ul>
        <li><a href = "userprofile">USERPROFILE</li>
        <li><a href = "assignment">ASSIGNMENTS</li>
        <li><a href = "pages">PAGES</li>
        <li><a href = "userlist">USERLIST</li>
        <li><a href = "attendance">ATTENDANCE</li>
    </ul>
    <%@ include file = "sidebar.jsp" %>
</body>
</html>