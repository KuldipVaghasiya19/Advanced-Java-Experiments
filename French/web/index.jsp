<%@page language="java" contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="fmt" uri="jakarta.tags.fmt" %>

<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSTL i18n</title>
    </head>
    <body>
        <h1>JSTL i18n Demo Page</h1>
        
        <fmt:setLocale value="fr_FR"/>
        <fmt:bundle baseName="com.example.language.messages">
            <fmt:message key="welcome.message"/> <br>
            <fmt:message key="exit.message"/> <br>
        </fmt:bundle>
            
    </body>
</html>
