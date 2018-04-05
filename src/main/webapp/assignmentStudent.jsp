<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Assigments</h1>
<ul>
    <div class = "sidenav">
<c:forEach var="t" items="${assignments}">
    <li>
    <a href="solution?id=<c:out value='${t.id}'/>"><c:out value="${t.question}"/></a>
    </li>
</c:forEach></div>
<p>
<a href="userpage">Go back</a>
</p>
</body>