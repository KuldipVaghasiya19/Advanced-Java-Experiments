<%@taglib uri="jakarta.tags.core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL/even odd check</title>
    
    </head>
    <body>
        <form action="index.jsp" method="get">
            <input type="text" id="number" name="item" required>
            <input type="submit" value="Check">
        </form>

        <c:if test="${not empty param.item}">
            <c:set var="number" value="${param.item}" />
            <c:choose>
                <c:when test="${number % 2 == 0}">
                    <p>${number} is even.</p>
                </c:when>
                    <c:when test="${number % 2 != 0}">
                    <p>${number} is odd.</p>
                </c:when>
               
            </c:choose>
        </c:if>
                    
    </body>
</html>