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
<a href="userpage">Go back</a>
    <table>
        <tr><th>Name</th><th>attendance</th></tr>
        <c:forEach var="u" items="${users}">
                    tr>
                        <td><c:out value = "${u.name}"/><p></td>
                        <td><form action="pages" method="post">
                                                        <input type="radio" name="${t.title}" value="true" <c:if test = "${t.isPublished}">
                                                                                                                      checked
                                                                                                                   </c:if> > Publish<br>
                                                        <input type="radio" name="${t.title}" value="false" <c:if test = "${!t.isPublished}">
                                                                                                                     checked
                                                                                                                    </c:if> > Unpublish<br>
                                                        <input type="submit" value="Submit">
                                                        </form></td>



                    </tr>
        </c:forEach>
    </table>
</body>
</html>