import java.io.*;
import java.sql.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class LoginServlet extends HttpServlet {

    private final String JDBC_URL = "jdbc:mysql://localhost:3306/exam_system";
    private final String DB_USER = "root";
    private final String DB_PASSWORD = "Kuldip19";

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        Connection conn = null;
        PreparedStatement stmt = null;
        HttpSession session = null;

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        try {
            // Load JDBC driver and establish connection
            Class.forName("com.mysql.cj.jdbc.Driver");
            conn = DriverManager.getConnection(JDBC_URL, DB_USER, DB_PASSWORD);

            // Check if the user exists and the password matches
            String query = "SELECT fullName FROM students WHERE userID = ? AND password = ?";
            stmt = conn.prepareStatement(query);
            stmt.setString(1, userID);
            stmt.setString(2, password);

            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                // User authenticated, create session
                session = request.getSession();
                session.setAttribute("userID", userID);
                session.setAttribute("fullName", rs.getString("fullName"));

                // Redirect to result viewing page
                response.sendRedirect("viewResult");
            } else {
                // Authentication failed, return error message
                out.println("<h3>Invalid User ID or Password</h3>");
            }

        } catch (Exception e) {
            e.printStackTrace();
            out.println("<h3>Error: " + e.getMessage() + "</h3>");
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
