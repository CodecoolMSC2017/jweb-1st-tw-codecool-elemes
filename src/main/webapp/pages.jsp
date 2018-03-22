<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Pages</h1>
<ul>
<c:forEach var="t" items="${texts}">
    <li>
    <a href="content?id=<c:out value='${t.id}'/>"><c:out value="${t.title}"/></a>
    </li>
</c:forEach>
<p>
<a href="userpage">Go back to the <em>homepage</em> page.</a>
</p>
</body>