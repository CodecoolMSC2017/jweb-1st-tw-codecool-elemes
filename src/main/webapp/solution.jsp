<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<h1>Solution Page</h1>
<a href="assignment">Go back to the <em>assignment</em> page.</a>
<ul>
<p>
    <c:out value="${question}"/>
        <form action="solution" method="post">
            <input type = "text" name="answer">
            <input type="submit" value="submit">
        </form>

</p>
</body>