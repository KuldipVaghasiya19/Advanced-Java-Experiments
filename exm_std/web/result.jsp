<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="java.sql.*" %>
<!DOCTYPE html>
<html>
<head>
    <title>Exam System - Result</title>
</head>
<body>
    <h2>Student Information</h2>
    
    <jsp:useBean id="student" class="bean.Student" scope="session"/>
    
    <!-- Set bean properties using submitted form data -->
    <jsp:setProperty name="student" property="userID" value="${param.userID}"/>
    <jsp:setProperty name="student" property="password" value="${param.password}"/>
    

    <h3>Details Submitted:</h3>
    <p>User ID: <jsp:getProperty name="student" property="userID"/></p>
    

    <h3>Fetching from Database:</h3>
    <%
        // JDBC connection details
        String url = "jdbc:mysql://localhost:3306/exam_system"; // Change to your DB URL
        String user = "root"; // Replace with your DB username
        String password = "Kuldip19"; // Replace with your DB password

        Connection con = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            Class.forName("com.mysql.cj.jdbc.Driver"); // Load MySQL JDBC Driver
            con = DriverManager.getConnection(url, user, password);
            ps = con.prepareStatement("SELECT * FROM students WHERE userID = ?");
            ps.setString(1, student.getUserID());
            rs = ps.executeQuery();

            if (rs.next()) {
                // Display fetched data
                out.println("<p>Fetched User ID: " + rs.getString("userID") + "</p>");
                out.println("<p>Fetched Full Name: " + rs.getString("fullName") + "</p>");
                out.println("<p>Fetched Semester: " + rs.getString("semester") + "</p>");
                out.println("<p>Fetched Roll No: " + rs.getString("rollNo") + "</p>");
                out.println("<p>Fetched Email: " + rs.getString("email") + "</p>");
                out.println("<p>Fetched Contact No: " + rs.getString("contactNo") + "</p>");
            } else {
                out.println("<p>No student found with User ID: " + student.getUserID() + "</p>");
            }
        } catch (Exception e) {
            e.printStackTrace();
            out.println("<p>Error fetching data.</p>");
        } finally {
            if (rs != null) try { rs.close(); } catch (SQLException e) {}
            if (ps != null) try { ps.close(); } catch (SQLException e) {}
            if (con != null) try { con.close(); } catch (SQLException e) {}
        }
    %>
</body>
</html>
