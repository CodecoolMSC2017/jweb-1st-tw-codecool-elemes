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
<%@ include file = "userpage.jsp" %>
<p><c:out value="${solution.assignment.question}"/></p>
<p>Max Score: <c:out value="${solution.assignment.maxScore}"/></p>
<p>Answer: <c:out value="${solution.assignment.answear}"/>
<form action="grade" method="post">
  <input type = "text" name="grade">
  <input type="hidden" name="id" value="${solution.id}">
  <input type="submit" value="submit">
</form>
<p><c:out value="${message}"/></p>
</body>