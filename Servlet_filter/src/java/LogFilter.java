import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.FilterConfig;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.annotation.WebFilter;

import java.io.IOException;
import java.util.Date;

@WebFilter("/HelloServlet")
public class LogFilter implements Filter {

    public void init(FilterConfig fConfig) throws ServletException {}

    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        // Log the request details
        System.out.println("Request received at: " + new Date());
        System.out.println("Client IP: " + request.getRemoteAddr());

        // Continue to the next filter or target servlet
        chain.doFilter(request, response);
    }

    public void destroy() {}
}
