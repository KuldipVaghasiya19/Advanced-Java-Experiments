import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;

@WebServlet("/HelloServlet")
public class HelloServlet extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // Set content type to HTML
        response.setContentType("text/html");

        // Build the HTML response
        response.getWriter().write("<!DOCTYPE html>");
        response.getWriter().write("<html lang='en'>");
        response.getWriter().write("<head>");
        response.getWriter().write("<meta charset='UTF-8'>");
        response.getWriter().write("<meta name='viewport' content='width=device-width, initial-scale=1.0'>");
        response.getWriter().write("<title>Welcome</title>");
        response.getWriter().write("</head>");
        response.getWriter().write("<body>");
        
        // Simple welcome message
        response.getWriter().write("<h1>Entered in Exam System</h1>");
        response.getWriter().write("<p>You are successfully Logged In.</p>");

        response.getWriter().write("</body>");
        response.getWriter().write("</html>");
    }
}
