<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>elemes</title>
    <link rel="stylesheet" href="style.css" type = "text/css" media = "screen">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
</head>

<body>
    <div class = "register">
        <header>
            <h1>
                <img src = "cclogo.png" width = "40px" height = "40px">LMS
            </h1>
        </header>
            <form action="register" method="post">
                <input type="text" name="name" placeholder = "Username"><br>
                <input type="text" name="email" placeholder = "Email"><br>
                <input type="radio" name="role" value="STUDENT" checked> Student
                <input type="radio" name="role" value="MENTOR"> Mentor<br>
                <input type="submit" value="Sign up">
            </form><br>
        <p>${message}</p>
        <p>${error}</p>
        <p id = "terms&policy">By signing up, you agree to our <strong>Terms & Privacy Policy</strong>.</p>
    </div>
    <div class = "backtologin">
        <p>Have an account?
        <a href="login">Login page</a></p>
    </div>
</body>
</html>