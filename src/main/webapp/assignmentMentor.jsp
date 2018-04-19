<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<body>
<%@ include file = "userpage.jsp" %>
<div class="pageContent">
    <ul>
        <form action="assignment" method="post">
            <c:forEach var="t" items="${assignments}">
                <li>
                    <div style="text-align: center">
                        <a href="listsolutions?id=<c:out value='${t.id}'/>">
                            <c:out value="${t.question}"/>
                        </a>
                    </div>

                    <input type="radio" name="${t.question}" value="true"
                    <c:if test="${t.isPublished}">
                        checked
                    </c:if>
                    > Publish<br>
                    <input type="radio" name="${t.question}" value="false"
                    <c:if test="${!t.isPublished}">
                        checked
                    </c:if>
                    > Unpublish<br>

                </li>
            </c:forEach>
            <div style="text-align: center">
                <input type="submit" value="Submit">
            </div>
        </form>
    </ul>
</div>
<form class="addassignment" action="addassignment" method="post">
    <br>
    <p>Add assignment</p>
    <input type="text" name="question" placeholder="Question"><br>
    <input type="text" name="score" placeholder="Score"><br>
    <input type="submit" value="Submit">
</form>
<jsp:include page="snippets/errorpage.jsp"/>
</body>



