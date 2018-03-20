<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Pages</h1>

<c:forEach var="t" items="${texts}">
    <p><c:out value="${t.text}"/></p>
    <form action="pages" method="post">
    <input type="radio" name="${t.text}" value="true" <c:if test = "${t.isPublished}">
                                                                  checked
                                                               </c:if> > Publish<br>
    <input type="radio" name="${t.text}" value="false" <c:if test = "${!t.isPublished}">
                                                                 checked
                                                                </c:if> > Unpublish<br>
    <input type="submit" value="Submit">
    	</form>
</c:forEach>

<a href="userpage">Go back to the <em>homepage</em> page.</a>
</body>



