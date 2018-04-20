<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html lang="en" xmlns:c="http://www.w3.org/1999/XSL/Transform">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>UserList</title>
    <link rel="stylesheet" href="style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <script src="https://code.jquery.com/jquery-1.12.4.js"></script>
    <script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
    <script>
  $( function() {
    $( "#datepicker" ).datepicker({ dateFormat: 'yy-mm-dd' },{minDate : -60, maxDate:"+0D"});
  } );
  </script>
    <style>
        table {
    font-family: arial, sans-serif;
    border-collapse: collapse;
    width: 20%;
}

td, th {
    border: 1px solid #dddddd;
    text-align: center;
    padding: 8px;
}

tr:nth-child(even) {
    background-color: #dddddd;
}
    </style>
</head>
<%@ include file = "userpage.jsp" %>
<body>
<form action="attendance" method="post">
    <p>Date: <input type="text" id="datepicker" value="${defaultDate}" name = "attendanceDate">
        <input type = "submit" value = "submit" width="6em">
    </p>
    <table align = "center">
        <tr>
            <th>Students</th>
            <th width="10%">Missing</th>
        </tr>
    <c:forEach var="s" items="${editAttendanceMap}">
        <tr>
            <td>${s.key.name}</td>
            <td width="10%"><input type="checkbox" name="${s.key.eMail}" value="true"
                <c:if test = "${s.value}">
                    checked
                </c:if></td>

        </tr>
    </c:forEach>
    </table>
    <form action = "editAttendance" method = "post">
        <input type = "submit" value = "edit" ailgn = "center">
    </form>
    <p>${error}</p>
    </form>
</body>
</html>