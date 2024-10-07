<%@ page import="Beans.Person" %>
<jsp:useBean id="person" class="Beans.Person" scope="session" />
<%
    String userID = request.getParameter("userID");
    String password = request.getParameter("password");

    if (person.authenticate(userID, password)) {
        response.sendRedirect("displayDetails.jsp");
    } else {
        out.println("<h2>Invalid Credentials. Please try again.</h2>");
    }
%>
