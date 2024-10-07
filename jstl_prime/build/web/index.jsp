<%@ taglib uri="jakarta.tags.core" prefix="c" %>
<!DOCTYPE html>
<html>
    <head>
        <title>JSTL/Prime Check</title>
    </head>
    <body>
        <form action="index.jsp" method="get">
            <input type="text" id="number" name="item" required>
            <input type="submit" value="Check">
        </form>

        <c:if test="${not empty param.item}">
            <c:set var="number" value="${param.item}" />
            <c:set var="isPrime" value="true" />
            
            <!-- Check if number is less than or equal to 1 -->
            <c:if test="${number <= 1}">
                <c:set var="isPrime" value="false" />
            </c:if>

            <!-- Loop to check for factors -->
            <c:forEach begin="2" end="${number - 1}" var="i">
                <c:if test="${number % i == 0}">
                    <c:set var="isPrime" value="false" />
                </c:if>
            </c:forEach>

            <!-- Display result -->
            <c:if test="${isPrime}">
                <p>${number} is prime.</p>
            </c:if>
            <c:if test="${not isPrime}">
                <p>${number} is not prime.</p>
            </c:if>
        </c:if>
    </body>
</html>
