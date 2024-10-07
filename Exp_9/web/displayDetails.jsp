<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<jsp:useBean id="person" class="Beans.Person" scope="session" />
<!DOCTYPE html>
<html>
<head>
    <title>User Details</title>
</head>
<body>
    <h2>Personal Details</h2>
    <p><strong>Full Name:</strong> <jsp:getProperty name="person" property="fullName" /></p>
    <p><strong>Semester:</strong> <jsp:getProperty name="person" property="semester" /></p>
    <p><strong>Roll Number:</strong> <jsp:getProperty name="person" property="rollNo" /></p>
    <p><strong>Email:</strong> <jsp:getProperty name="person" property="email" /></p>
    <p><strong>Contact Number:</strong> <jsp:getProperty name="person" property="contactNo" /></p>
</body>
</html>
