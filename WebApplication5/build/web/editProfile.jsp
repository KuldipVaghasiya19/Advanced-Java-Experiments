<%@ taglib uri="http://java.sun.com/jsp/jstl/sql" prefix="sql" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<sql:setDataSource var="myDataSource" driver="com.mysql.cj.jdbc.Driver"
                   url="jdbc:mysql://localhost:3306/exam_system"
                   user="root" password="Kuldip19"/>

<!-- Login Validation -->
<c:choose>
    <c:when test="${empty param.fullName}">
        <!-- If the fullName parameter is not present, assume this is the first login validation request -->
        <!-- Query to authenticate user -->
        <sql:query var="result" dataSource="${myDataSource}">
            SELECT * FROM students WHERE userID = ? AND password = ?
            <sql:param value="${sessionScope.userID}" />
            <sql:param value="${sessionScope.password}" />
        </sql:query>

        <c:choose>
            <c:when test="${not empty result.rows}">
                <!-- If user is authenticated, store user details in session scope -->
                <c:set var="user" value="${result.rows[0]}" scope="session"/>

                <!-- Display the form to edit user details -->
                <h2>Welcome, ${user.fullName}</h2>
                <form action="editProfile.jsp" method="post">
                    <label for="fullName">Full Name:</label>
                    <input type="text" id="fullName" name="fullName" value="${user.fullName}" required><br>

                    <label for="semester">Semester:</label>
                    <input type="text" id="semester" name="semester" value="${user.semester}" required><br>

                    <label for="rollNo">Roll No:</label>
                    <input type="text" id="rollNo" name="rollNo" value="${user.rollNo}" required><br>

                    <label for="email">Email:</label>
                    <input type="email" id="email" name="email" value="${user.email}" required><br>

                    <label for="contactNo">Contact No:</label>
                    <input type="text" id="contactNo" name="contactNo" value="${user.contactNo}" required><br>

                    <input type="submit" value="Update">
                </form>
            </c:when>
            <c:otherwise>
                <!-- If login fails -->
                <h3>Invalid UserID or Password</h3>
                <a href="index.jsp">Try Again</a>
            </c:otherwise>
        </c:choose>
    </c:when>

    <c:otherwise>
        <!-- If the fullName parameter is present, this is an update request -->
        <!-- Update user details -->
        <sql:update dataSource="${myDataSource}">
            UPDATE students SET fullName = ?, semester = ?, rollNo = ?, email = ?, contactNo = ?
            WHERE userID = ?
            <sql:param value="${param.fullName}" />
            <sql:param value="${param.semester}" />
            <sql:param value="${param.rollNo}" />
            <sql:param value="${param.email}" />
            <sql:param value="${param.contactNo}" />
            <sql:param value="${sessionScope.userID}" />
        </sql:update>

        <h3>Your details have been successfully updated!</h3>
        <a href="editProfile.jsp">Go back to edit page</a><br>
        <a href="index.jsp">Go back to home page</a>

    </c:otherwise>
</c:choose>
