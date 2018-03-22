<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Assignments</h1>
<a href="userpage">Go back to the <em>homepage</em> page.</a>
<ul>
<c:forEach var="t" items="${assignments}">
    <li>
    <a href="listsolutions?id=<c:out value='${t.id}'/>"><c:out value="${t.question}"/></a>
    <form action="assignment" method="post">
    <input type="radio" name="${t.question}" value="true" <c:if test = "${t.isPublished}">
                                                                  checked
                                                               </c:if> > Publish<br>
    <input type="radio" name="${t.question}" value="false" <c:if test = "${!t.isPublished}">
                                                                 checked
                                                                </c:if> > Unpublish<br>
    <input type="submit" value="Submit">
    </form>
    </li>
</c:forEach>


