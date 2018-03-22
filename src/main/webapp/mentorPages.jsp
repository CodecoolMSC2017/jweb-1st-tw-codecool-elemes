<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Pages</h1>
<a href="userpage">Go back to the <em>homepage</em> page.</a>
<ul>
<c:forEach var="t" items="${texts}">
    <li>
    <a href="content?id=<c:out value='${t.id}'/>"><c:out value="${t.title}"/></a>
    <form action="pages" method="post">
    <input type="radio" name="${t.title}" value="true" <c:if test = "${t.isPublished}">
                                                                  checked
                                                               </c:if> > Publish<br>
    <input type="radio" name="${t.title}" value="false" <c:if test = "${!t.isPublished}">
                                                                 checked
                                                                </c:if> > Unpublish<br>
    <input type="submit" value="Submit">
    </form>
    </li>
</c:forEach>
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



