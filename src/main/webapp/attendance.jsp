<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance</title>
</head>
<body>
<h1>Attendance</h1>
<a href="userpage">Go back to the homepage</a>
    <table>
        <tr><th>Name</th><th>attendance</th></tr>
        <c:forEach var="u" items="${users}">
                    tr>
                        <td><c:out value = "${u.name}"/><p></td>
                        <td><c:out value = "${user.email}"/><p></td>
                        <td><c:out value = "${user.role}"/><p></td>

                    </tr>
        </c:forEach>
    </table>
</body>
</html>