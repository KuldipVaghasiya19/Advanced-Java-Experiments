<!-- WebContent/index.jsp -->
<!DOCTYPE html>
<html>
<head>
    <title>Enter Username</title>
    <meta charset="UTF-8">
</head>
<body>
    <h1>Form</h1>
    <form action="myServlet" method="post">
        <label for="username">Username:</label>
        <input type="text" name="username" id="username" required>
        <button type="submit">Submit</button>
    </form>
</body>
</html>
