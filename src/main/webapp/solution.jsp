<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ include file =  "userpage.jsp" %>
<body>
<h1>Solution Page</h1>
<a href="assignment">Go back</a>
<ul>
<c:if test="${not empty question}">
   <p>
       <c:out value="${question}"/>
           <form action="solution" method="post">
               <input type = "text" name="answer">
               <input type="hidden" name="question" value="${question}">
               <input type="submit" value="submit">
           </form>
   </p>
</c:if>
    <c:if test="${not empty quest}">
        <p>Question: <c:out value="${quest}"/></p>
        <p>Your Answear: <c:out value="${answer}"/></p>
        <p>Grade:
            <c:if test="${not empty grade}">
                <c:out value="${grade}"/></p>
            </c:if>
            <c:if test="${empty grade}">
                <c:out value="not yet graded"/></p>
            </c:if>
    </c:if>
    <p><c:out value="${backmessage}"/></p>
</body>