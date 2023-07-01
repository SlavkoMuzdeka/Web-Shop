<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="java.util.*"%>
<%@ page import="org.unibl.etf.dto.Message"%>
<%@ page import="javax.mail.*"%>
<%@ page import="javax.mail.internet.*"%>

<jsp:useBean id="messageBean" class="org.unibl.etf.beans.MessageBean"
	scope="application" />
<jsp:useBean id="adminBean" class="org.unibl.etf.beans.AdminBean"
	scope="session" />

<%
if (!adminBean.isLoggedIn()) {
	response.sendRedirect("login.jsp");
}
%>

<!DOCTYPE html>
<html>
<head>
<title>User-Support Application</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/pomScript.js"></script>
</head>
<body>
	<div class="panel panel-default">
		<div class="container-fluid">
			<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
				<a class="navbar-brand">User Support App</a>
				<form class="form-inline ml-auto" action="logout.jsp" method="POST">
					<button class="btn btn-danger" type="submit">Logout</button>
				</form>
			</nav>
		</div>
		<div class="container-fluid">
			<div class="row">
				<div class="col-md-2" style="padding-left: 30px; padding-top: 15px">
					<div class="row">
						<p class="mb-0" style="padding-right: 5px;">Shown</p>
						<select class="selectpicker" data-style="btn-success"
							id="typeOfShownMess" onChange="modifyTableView()">
							<option>All</option>
							<option>Reviewed</option>
							<option>Unreviewed</option>
						</select>
					</div>
				</div>
				<div class="col-md-6"></div>
				<div class="col-md-2" style="padding-top: 15px"></div>
				<div class="col-md-2" style="padding-top: 10px">
					<div class="input-group">
						<input class="form-control" id="searchContent" type="text"
							placeholder="Search.." onkeyup="searchContent()">
					</div>
				</div>
			</div>
			&nbsp;
			<div class="table-container"
				style="height: calc(100vh - 140px);; overflow-y: scroll;">
				<table class="table table-dark table-hover table-striped"
					id="messagesTable">
					<thead class="text-center">
						<tr>
							<th class="col-3">Title</th>
							<th class="col-5">Content</th>
							<th class="col-2">Reviewed</th>
							<th class="col-2">Check</th>
						</tr>
					</thead>
					<tbody id="tableBodyQuestions" class="text-center">
						<%
						for (Message q : messageBean.getUserSupports()) {
						%>
						<tr>
							<td class="cell-ellipsis"><%=q.getTitle()%></td>
							<td class="cell-ellipsis"><%=q.getContent()%></td>
							<td class="form-check"><input type="checkbox"
								name="reviewed" id="reviewed_<%=q.getId()%>"
								<%=q.getStatus() ? "checked disabled" : "unchecked disabled"%>
								class="form-check-input"></td>
							<td>
								<button type="button" class="btn btn-primary"
									onclick="showModal('<%=q.getTitle()%>', '<%=q.getContent()%>', '<%=q.getUser().getEmail()%>', '<%=q.getId()%>', 'reviewed_<%=q.getId()%>')">
									Check</button>
							</td>
						</tr>
						<%
						}
						%>
					</tbody>
				</table>
			</div>
		</div>
	</div>
	<%@include file="WEB-INF/modal.jsp"%>
</body>
</html>