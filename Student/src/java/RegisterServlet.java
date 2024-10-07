// File: RegisterServlet.java
import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class RegisterServlet extends HttpServlet {

    // Database connection details
    private final String JDBC_URL = "jdbc:mysql://localhost/exam_system";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Kuldip19";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Get PrintWriter to write HTML response
        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        // Retrieve form data using request.getParameter()
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");
        String confirmPassword = request.getParameter("confirmPassword");
        String fullName = request.getParameter("fullName");
        String semester = request.getParameter("semester");
        String rollNo = request.getParameter("rollNo");
        String email = request.getParameter("email");
        String contactNo = request.getParameter("contactNo");

        // Check if passwords match
        if (!password.equals(confirmPassword)) {
            out.println("<h3>Passwords do not match!</h3>");
            return;
        }

        // JDBC connection
        Connection conn = null;
        PreparedStatement stmt = null;

        try {
            // Load JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Establish connection
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Check if userID is unique
            String checkUserIDQuery = "SELECT userID FROM students WHERE userID = ?";
            stmt = conn.prepareStatement(checkUserIDQuery);
            stmt.setString(1, userID);

            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                out.println("<h3>User ID already exists. Please choose a different one.</h3>");
            } else {
                // Insert the new user into the database
                String insertQuery = "INSERT INTO students (userID, password, fullName, semester, rollNo, email, contactNo) "
                                   + "VALUES (?, ?, ?, ?, ?, ?, ?)";
                stmt = conn.prepareStatement(insertQuery);
                stmt.setString(1, userID);
                stmt.setString(2, password);
                stmt.setString(3, fullName);
                stmt.setString(4, semester);
                stmt.setString(5, rollNo);
                stmt.setString(6, email);
                stmt.setString(7, contactNo);

                int rowsInserted = stmt.executeUpdate();
                if (rowsInserted > 0) {
                    out.println("<h3>Registration successful!</h3>");
                } else {
                    out.println("<h3>Registration failed. Please try again.</h3>");
                }
            }

        } catch (ClassNotFoundException | SQLException e) {
            out.println("<h3>Something went wrong: " + e.getMessage() + "</h3>");
        } finally {
            try {
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
