import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String userID = request.getParameter("userID");
        String password = request.getParameter("password");

        // Create a session and store userID and password
        HttpSession session = request.getSession();
        session.setAttribute("userID", userID);
        session.setAttribute("password", password);

        // Forward to the JSP for database operations using JSTL
        request.getRequestDispatcher("editProfile.jsp").forward(request, response);
    }
}
