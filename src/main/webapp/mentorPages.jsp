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
<h1>Pages</h1>
<a href="userpage">Go back to the <em>homepage</em> page.</a>
<div class = "sidenav">

<ul>
<c:forEach var="t" items="${texts}">
    <li>
        <a href="content?id=<c:out value='${t.id}'/>"><c:out value="${t.title}"/></a>
        <form action="pages" method="post">
            <input type="radio" name="${t.title}" value="true" <c:if test = "${t.isPublished}">checked</c:if> > Publish<br>
            <input type="radio" name="${t.title}" value="false" <c:if test = "${!t.isPublished}">checked</c:if> > Unpublish<br>                                                        checked
            <input type="submit" value="Submit" width:6em>
        </form>
    </li>
</c:forEach>

</div>
    <form action="addcontent" method="post">
        <p>Add content</p>
        <p>Title:<p>
        <input type="text" name ="title"><br>
        Content:<br>
        <input type="text" name ="content"><br>
        <input type="submit" value="Submit">
    </form>
    <p><c:out value="${message}" /></p>
</body>



