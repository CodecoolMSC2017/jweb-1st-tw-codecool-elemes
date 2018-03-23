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
<form method="get" action= "pages">
    <input type = "submit" value = "Go back" style = "width:8%; align : left;">
</form>

<div class = "textbox">
   <p>
   <c:forEach var="t" items="${content}">
        <c:out value="${t}"/><br>
   </c:forEach>
   </p>
</div>

</body>