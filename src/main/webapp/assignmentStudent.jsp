<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<%@ include file = "userpage.jsp" %>
<div class = "pageContent">
    <ul>
        <c:forEach var="t" items="${assignments}">
            <li>
                <div style="text-align: center">
                    <a href="solution?id=<c:out value='${t.id}'/>"><c:out value="${t.question}"/></a><br>
                </div>

            </li>
        </c:forEach>
    </ul>
</div>
</body>