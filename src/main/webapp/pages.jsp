<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Pages</h1>
<c:forEach var="t" items="${texts}">
    <p><c:out value="${t.text}"/></p>
</c:forEach>
<a href="userpage">Go back to the <em>homepage</em> page.</a>
</body>