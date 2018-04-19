<%@ page contentType="text/html; charset=UTF-8" %>
<%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<%@ include file =  "userpage.jsp" %>
<body>
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
            <c:if test="${grade gt 0}">
                <c:out value="${grade}"/></p>
            </c:if>
            <c:if test="${grade == 0}">
                <c:out value="not yet graded"/></p>
            </c:if>
    </c:if>
    <p><c:out value="${backmessage}"/></p>
</ul>
</body>