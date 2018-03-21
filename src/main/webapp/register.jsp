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
    elemes<br>
    <h1>Registration form</h1><br>
    <form action="register" method="post">
		Name:<br>
		<input type="text" name="name"><br>
		E-mail address:<br>
		<input type="text" name="email"><br>
		<input type="radio" name="role" value="STUDENT" checked> Student<br>
		<input type="radio" name="role" value="MENTOR"> Mentor<br>
		<input type="submit" value="Register">
	</form><br>
	<p>${message}</p>
	<p>${error}</p>
	<a href="login">Login page</a>
</body>
</html>