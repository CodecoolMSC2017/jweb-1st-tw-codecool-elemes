<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>elemes</title>
    <link rel="stylesheet" href="style.css">
<link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
</head>
<style>
	body{
    	background-color:#f9f9f9;
    }
    header{
    	color:black;
        text-align:left;
        font-size:15pt;
        padding-top : 0px;
        font-family: 'Roboto', sans-serif;
        text-shadow: 2px 2px #d3d1d1;
        margin-left:75px;

    }
    form{
    text-align:center;
    }
    input[type=submit] {
        background-color: #28c1ef;
        color: white;
        padding: 7px 7px;
        margin-top:10px;
        border: none;
        border-radius: 4px;
        cursor: pointer;
        float: center;
        width:250px;

    }
    input[type=text]{
    width: 250;
    padding: 7px;
    margin-top:7px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
    float:center;

}
 #register{
 	background-color:white;
    margin-top: 10%;
    margin-left:20%;
    margin-right:20%;
    border: 1px solid #ccc;
    margin-bottom:20px;

 }

 p {
 text-align:center;
 color:#bcbcbc;
 }
</style>
<body>
<div id = "register">
    <header><h1>Sign up</h1></header>
    <form action="register" method="post">
        <input type="text" name="name" placeholder = "Username"><br>
        <input type="text" name="email" placeholder = "Email"><br>
        <input type="radio" name="role" value="STUDENT" checked> Student
        <input type="radio" name="role" value="MENTOR"> Mentor<br>
        <input type="submit" value="Sign up">
    </form><br>
    <p>${message}</p>
    <p>${error}</p>
    <p>By signing up, you agree to our <strong>Terms & Privacy Policy</strong>.</p>
<div>

	<a href="login">Login page</a>
</body>
</html>