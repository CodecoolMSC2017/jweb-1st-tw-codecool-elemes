<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ taglib prefix = "fmt" uri = "http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>

<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance</title>
    <link rel="stylesheet" href="style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
  $( function() {
    $( "#datepicker" ).datepicker({minDate : -60, maxDate:"+0D"});
  } );
  </script>
</head>
<%@ include file = "userpage.jsp" %>

<body>
<a href="userpage">Go back</a>
<form action="attendance" method="post">
    <p>Date: <input type="text" id="datepicker" name = "attendanceDate"></p>
    <c:forEach var="s" items="${students}">
        <input type="checkbox" name="${s.eMail}" value="true">${s.name}<br>
    </c:forEach>
    <input type="submit" value="Submit" width=6em; />
</form>
<table>
    <tr><th>Days</th><th>People</th></tr>
    <c:forEach var="attendance" items="${AllOverAttendance}">
        <tr>
            <td><fmt:formatDate pattern = "yyyy-MM-dd" value = "${attendance.key}" /></td>
            <td>
                <ul>
                    <c:forEach items="${attendance.value}" var = "user">
                        <li>${user.name}</li>
                    </c:forEach>
                </ul>
            </td>
        </tr>
    </c:forEach>
</table>
<form action = "editAttendance" method = "get" style="text-align:right;">
    <input type = "submit" value = "edit" style=" margin-right:5%;">
</form>

</html>
</body>
</html>