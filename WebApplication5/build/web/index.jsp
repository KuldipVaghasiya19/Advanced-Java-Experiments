<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Login Page</title>
</head>
<body>
    <h2>Login to Edit Your Profile</h2>
    <form action="LoginServlet" method="post">
        <label for="userID">User ID:</label>
        <input type="text" id="userID" name="userID" required><br>
        <label for="password">Password:</label>
        <input type="password" id="password" name="password" required><br>
        <input type="submit" value="Login">
    </form>
</body>
</html>
