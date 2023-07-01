<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<jsp:useBean id="adminBean" class="org.unibl.etf.beans.AdminBean"
	scope="session" />
<jsp:useBean id="messageBean" class="org.unibl.etf.beans.MessageBean"
	scope="application" />

<%
if (!adminBean.isLoggedIn())
	response.sendRedirect("login.jsp");
String id = request.getParameter("id");
if (id != null) {
	messageBean.setMessageAsReviewed(Integer.parseInt(id));
}
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

</body>
</html>