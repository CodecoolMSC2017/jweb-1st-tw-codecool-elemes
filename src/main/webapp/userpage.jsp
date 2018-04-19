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

    <%@ include file = "header.jsp" %>
    <div class="sidenav">
        <a href="userprofile">PROFILE</a>
        <a href="assignment">ASSIGNMENTS</a>
        <a href="pages">PAGES</a>
        <a href="userlist">USERS</a>
        <a href="${redirect}">ATTENDANCE</a>
        <a href="statistics">STATISTICS</a>
    </div>
</body>
</html>