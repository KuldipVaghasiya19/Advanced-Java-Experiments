import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

@WebFilter("/HelloServlet")
public class AuthenticationFilter implements Filter {

    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/exam_system";
    private static final String JDBC_USER = "root";  // Replace with your MySQL username
    private static final String JDBC_PASSWORD = "Kuldip19";  // Replace with your MySQL password

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        if (isAuthenticated(userID, password)) {
            // If authenticated, continue to the servlet
            chain.doFilter(request, response);
        } else {
            // If authentication fails, show an error message
            response.getWriter().write("Authentication Failed! Invalid UserID or Password.");
        }
    }

    private boolean isAuthenticated(String userID, String password) {
        boolean isAuthenticated = false;
        Connection connection = null;
        PreparedStatement statement = null;
        ResultSet resultSet = null;

        try {
            // Load MySQL JDBC Driver
            Class.forName("com.mysql.cj.jdbc.Driver");

            // Establish the database connection
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);

            // Query to check the user's credentials
            String sql = "SELECT * FROM students WHERE userID = ? AND password = ?";
            statement = connection.prepareStatement(sql);
            statement.setString(1, userID);
            statement.setString(2, password);

            resultSet = statement.executeQuery();

            // If resultSet has data, it means user is authenticated
            isAuthenticated = resultSet.next();
        } catch (SQLException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            // Clean up the database resources
            try {
                if (resultSet != null) resultSet.close();
                if (statement != null) statement.close();
                if (connection != null) connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

        return isAuthenticated;
    }

    public void destroy() {}
}
