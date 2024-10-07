<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exam System - Student Entry</title>
</head>
<body>
    <h1>Enter Student Details</h1>
    <form action="result.jsp" method="post">
        <label for="userID">User ID:</label>
        <input type="text" name="userID" required/><br/>

        <label for="password">Password:</label>
        <input type="password" name="password" required/><br/>

       
        <button type="submit">Submit</button>
    </form>
</body>
</html>
