<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserList</title>
    <link rel="stylesheet" href="style.css" type="text/css" media="screen">
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
<form action="editAttendance" method="post">
    <p>Date: <input type="text" id="datepicker" name = "editableDate">
        <input type = "submit" value = "submit" width="6em">
    </p><br><br>
    <c:forEach var="s" items="${editAttendanceMap}">
        <input type="checkbox" name="${s.key.eMail}" value="true"
            <c:if test = "${s.value}">
                checked
            </c:if>
        >
        ${s.key.name}<br>
    </c:forEach>
</form>
</body>
</html>