<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserList</title>
    <link rel="stylesheet" href="style.css">

</head>
<%@ include file = "userpage.jsp" %>
<body>
<h1>Users</h1>
<a href="userpage">Go back</a>
<c:forEach var="u" items="${users}">
    <div class = "users">
        <p>Name: <c:out value="${u.name}"/><br>Email: <c:out value="${u.eMail}"/><br>Role: <c:out value="${u.role}"/><p>
    </div>
</c:forEach>
</body>
</html>