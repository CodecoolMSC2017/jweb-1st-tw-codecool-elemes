<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>elemes</title>
    <link rel="stylesheet" href="style.css" type = "text/css">
    <link href="https://fonts.googleapis.com/css?family=Roboto" rel="stylesheet">
    <link href="https://fonts.googleapis.com/css?family=Anton" rel="stylesheet">
</head>
<style>
	body{
    	background-color:#f9f9f9;
    }
    header{
    	color:black;
        text-align:center;
        font-size:15pt;
        padding-top : 0px;
        font-family: 'Roboto', sans-serif;
        text-shadow: 2px 2px #d3d1d1;



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
        width:230px;

    }
    input[type=text], textarea {
    width: 250;
    padding: 7px;
    margin-top:7px;
    border: 1px solid #ccc;
    border-radius: 4px;
    resize: vertical;
    float:center;

}
 #login{
 	background-color:white;
    margin-top: 10%;
    margin-left:30%;
    margin-right:30%;
    border: 1px solid #ccc;
    margin-bottom:20px;

 }
 #signup{
 	background-color:white;
    margin-left:30%;
    margin-right:30%;
    border: 1px solid #ccc;
    margin-bottom:15%;
 }
 p {
 text-align:center;
 }
</style>
<body>
<div id = "login">
    <header>
        <h1>Login</h1>
    </header>
    <p>${login_error}</p>
    <form action="login" method="post">
		<input type="text" name="email" placeholder = "e-mail"><br>
		<input type="submit" value="Login">
	</form>
	<p>${message}</p><br>

   </div>
   <div id = "signup">
   	<p>Don't have an account?
    <a href="register">Sign up</a></p>
   </div>

    <%@ include file = "footer.jsp" %>
</body>
</html>