<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserList</title>
</head>
<body>
<h1>users</h1>
<a href="userpage">Go back to the homepage</a>
<c:forEach var="u" items="${users}">
    <p><c:out value="${u.name}"/>: <c:out value="${u.role}"/><p>
</c:forEach>
</body>
</html>