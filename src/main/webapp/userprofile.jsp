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
<h1>User Profile</h1>
<a href="userpage">Go back to the homepage</a>
<p>Current profile<p>
<p>Name: <c:out value="${user.name}"/><br>Email: <c:out value="${user.eMail}"/><br>Role: <c:out value="${user.role}"/><p>

<form action="registration" method="post">
		Name:<br>
		<input type="text" name="name"><br>
		Role:<br>
		<input type="radio" name="role" value="STUDENT" checked> Student<br>
		<input type="radio" name="role" value="MENTOR"> Mentor<br>
		<input type="submit" value="Save">
	</form>
	<p>${message}</p>
</body>
</html>