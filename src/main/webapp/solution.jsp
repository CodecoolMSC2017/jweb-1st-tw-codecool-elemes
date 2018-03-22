<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Solution Page</h1>
<a href="assignment">Go back</a>
<ul>
<p>
    <c:out value="${question}"/>
        <form action="solution" method="post">
            <input type = "text" name="answer">
            <input type="hidden" name="question" value="${question}">
            <input type="submit" value="submit">
        </form>

</p>
<p><c:out value="${backmessage}"/></p>
</body>