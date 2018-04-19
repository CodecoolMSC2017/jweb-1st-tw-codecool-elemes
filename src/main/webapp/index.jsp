<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>elemes</title>
    <link rel="stylesheet" href="style.css" type = "text/css">
</head>
<body>
    <div class = "login">
        <header>
            <h1>
                <img src = "cclogo.png" width = "40px" height = "40px">elemes kenvesz
            </h1>
        </header>
        <p>${login_error}</p>
        <form action="login" method="post">
            <input type="email" name="email" placeholder = "e-mail" size ="20"><br>
            <input type="submit" value="Login" style = "width:150px;">
        </form>
        <p>${message}</p><br>

    </div>
    <div class = "signup">
        <p>Don't have an account?
        <a href="register">Sign up</a></p>
    </div>
   <%@ include file = "footer.jsp" %>
</body>
</html>