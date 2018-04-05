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
<div class = "pageContent" style="align:center;">
<ul>
  <form action="pages" method="post">
      <c:forEach var="t" items="${texts}">
          <li>
              <a href="content?id=<c:out value='${t.id}'/>"><c:out value="${t.title}"/></a><br>
              <input type="radio" name="${t.title}" value="true" <c:if test = "${t.isPublished}">
                                                                            checked
                                                                         </c:if> > Publish<br>
              <input type="radio" name="${t.title}" value="false" <c:if test = "${!t.isPublished}">
                                                                           checked
                                                                          </c:if> > Unpublish<br>
        </li>
      </c:forEach>
      <input type="submit" value="Submit" style ="width: 6em;">
  </form >
</ul>
</div>
<form class="addcontent" action="addcontent" method="post" align = "center">
    <p>Add content</p>
    <input type="text" name ="title" placeholder = "Title"><br><br>
    <textarea name = "content"  rows = "20" cols = "100" wrap="hard" placeholder = "Write here..."></textarea><br>
    <input type="submit" value="Submit">
</form>
    <p><c:out value="${message}" /></p>
</body>



