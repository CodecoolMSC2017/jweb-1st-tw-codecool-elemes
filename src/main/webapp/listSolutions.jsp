<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Attendance</title>
    <link rel="stylesheet" href="style.css" type="text/css" media="screen">
    <link rel="stylesheet" href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">
    <link rel="stylesheet" href="/resources/demos/style.css">
    <%@ include file = "userpage.jsp" %>
</head>
<body>
<div class="listSolutions">
    <h1>Solutions</h1>
        <ul>
            <h3>
                Graded Solutions:
            </h3>
            <c:forEach var="t" items="${gradedSolutions}">
                <li>
                    <a href="grade?id=<c:out value='${t.id}'/>">
                        <c:out value="${t.assignment.question}"/>
                    </a>
                    <p>Subbmitted by
                        <c:out value="${t.user.name}"/>
                    </p>
                </li>
            </c:forEach>
            <h3>
                Solutions to grade:
            </h3>
            <c:forEach var="t" items="${solutionsToGrade}">
                <li>
                    <a href="grade?id=<c:out value='${t.id}'/>">
                        <c:out value="${t.assignment.question}"/>
                    </a>
                    <p>Subbmitted by
                        <c:out value="${t.user.name}"/>
                    </p>
                </li>
            </c:forEach>
    <p>
        <a href="userpage">Go back</a>
    </p>
</div>
</body>