<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="org.unibl.etf.email.service.EmailService"%>

<jsp:useBean id="adminBean" class="org.unibl.etf.beans.AdminBean"
	scope="session" />

<%
if(!adminBean.isLoggedIn())
	response.sendRedirect("login.jsp");

String from = request.getParameter("from");
String to = request.getParameter("to");
String subject = request.getParameter("subject");
String message = request.getParameter("message");
String answer = request.getParameter("answer");
if (to != null && subject != null && message != null && answer != null) {
	EmailService.sendEmail(from, to, subject, message, answer);
}
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Sending mail</title>
</head>
<body>

</body>
</html>