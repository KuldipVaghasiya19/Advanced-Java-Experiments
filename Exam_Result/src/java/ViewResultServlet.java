import java.io.*;
import jakarta.servlet.*;
import jakarta.servlet.http.*;

public class ViewResultServlet extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = response.getWriter();
        response.setContentType("text/html");

        HttpSession session = request.getSession(false); // Get the session, don't create if it doesn't exist

        if (session == null || session.getAttribute("userID") == null) {
            // No session, redirect to login
            out.println("<h3>You are not logged in. Please <a href='index.html'>login</a> first.</h3>");
        } else {
            // Retrieve session data and show results
            String userID = (String) session.getAttribute("userID");
            String fullName = (String) session.getAttribute("fullName");

            out.println("<h2>Welcome, " + fullName + "</h2>");
            out.println("<h3>Your Exam Result</h3>");
            // Placeholder for result details
            out.println("<p>Result for user " + userID + ": Passed with distinction!</p>");

            // Invalidate session (user logs out automatically after viewing the result)
            session.invalidate();
            out.println("<p>Session ended. You are logged out.</p>");
        }
    }
}
