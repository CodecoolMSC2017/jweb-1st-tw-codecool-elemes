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
    <h1>Login</h1><br>
    <form action="login" method="post">
		Name:<br>
		<input type="text" name="name"><br>
		E-mail address:<br>
		<input type="text" name="email"><br>
		<input type="submit" value="Login">
	</form>
	<p>${message}</p><br>
	<p>If you don't own an account yet, and would like to register, please
        <a href="registration.jsp">click here</a></p>
</body>
</html>