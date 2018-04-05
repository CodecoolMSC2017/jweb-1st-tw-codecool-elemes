<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>elemes</title>
    <link rel="stylesheet" href="style.css">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
</head>

<body>
<%@ include file = "userpage.jsp" %>
<div class = "pageContent" align = "center" >

<ul>
<c:forEach var="t" items="${texts}">
    <li>
    <a href="content?id=<c:out value='${t.id}'/>"><c:out value="${t.title}"/></a>
    </li>
</c:forEach>
</ul>
</div>
</body>