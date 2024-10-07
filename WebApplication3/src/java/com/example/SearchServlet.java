package com.example;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        
        // Set the content type
        response.setContentType("text/html");
        PrintWriter out = response.getWriter();
        
        // Extract query parameters
        String keywords = request.getParameter("keywords");

        // Retrieve header information
        String accept = request.getHeader("Accept");
        String acceptEncoding = request.getHeader("Accept-Encoding");
        String connection = request.getHeader("Connection");
        String cookie = request.getHeader("Cookie");
        String host = request.getHeader("Host");
        String referer = request.getHeader("Referer");
        String userAgent = request.getHeader("User-Agent");
        
        // Generate the HTML response
        out.println("<html><body>");
        out.println("<h1>HTTP 1.1 Request Details</h1>");
        
        out.println("<h2>Query String Parameters</h2>");
        out.println("<p><strong>Keywords:</strong> " + keywords + "</p>");
        
        out.println("<h2>Headers</h2>");
        out.println("<p><strong>Accept:</strong> " + accept + "</p>");
        out.println("<p><strong>Accept-Encoding:</strong> " + acceptEncoding + "</p>");
        out.println("<p><strong>Connection:</strong> " + connection + "</p>");
        out.println("<p><strong>Cookie:</strong> " + cookie + "</p>");
        out.println("<p><strong>Host:</strong> " + host + "</p>");
        out.println("<p><strong>Referer:</strong> " + referer + "</p>");
        out.println("<p><strong>User-Agent:</strong> " + userAgent + "</p>");
        
        // Display cookies if any
        out.println("<h2>Cookies</h2>");
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie c : cookies) {
                out.println("<p><strong>" + c.getName() + ":</strong> " + c.getValue() + "</p>");
            }
        } else {
            out.println("<p>No cookies found</p>");
        }

        out.println("</body></html>");
        out.close();
    }
}
