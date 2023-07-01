<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="org.unibl.etf.dto.User"%>
<%@ page import="java.util.Base64" %>

<jsp:useBean id="userBean" type="org.unibl.etf.beans.UserBean"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<title>Users</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="js/pomScript.js"></script>

</head>
<body>
	<%@include file="/WEB-INF/pages/header.jsp"%>
	<div class="row mt-3 justify-content-between px-3 mx-0">
  		<div class="col-6"><h3>Users</h3></div>
  		<div class="col-2 text-right">
  			<a href="UserController?action=createUser">
  				<button class="btn btn-success">Create New User</button>
  			</a>
  		</div>
	</div>
	<div class="container-fluid">
		<div class="table-container"
			style="height: calc(100vh - 130px); overflow-y: scroll;">
			<table class="table table-dark table-hover table-striped"
				id="usersTable">
				<thead class="text-center">
					<tr>
						<th class="col-2">Name</th>
						<th class="col-2">Surname</th>
						<th class="col-1">City</th>
						<th class="col-1">Username</th>
						<th class="col-1">Password</th>
						<th class="col-1">Mail</th>
						<th class="col-1">Avatar</th>
						<th class="col-2">Action</th>
					</tr>
				</thead>
				<tbody id="tableBodyUsers" class="text-center">
					<%
					for (User user : userBean.readAll()) {
					%>
					<tr>
						<td class="cell-ellipsis"><%=user.getFirstName()%></td>
						<td class="cell-ellipsis"><%=user.getLastName()%></td>
						<td class="cell-ellipsis"><%=user.getCity()%></td>
						<td class="cell-ellipsis"><%=user.getUserName()%></td>
						<td class="cell-ellipsis"><%=user.getPassword()%></td>
						<td class="cell-ellipsis"><%=user.getMail()%></td>
						<td>
							<%
							byte[] avatar = user.getAvatar();
 							if (avatar != null) {
								String base64Data = Base64.getEncoder().encodeToString(avatar);
 								%>
 								<img src="data:image/jpeg;base64,<%=base64Data%>" width="40" height="40">
 								<%
 							}else{
 								%>
 								<%= "-" %>
 							<%
 							}
 							%>
						</td>
						<td>
						<a href="?action=deleteUser&id=<%= user.getId()%>">
							<button class='btn btn-primary'><i class="fa fa-futboll-0">Delete</i></button>
						</a>
						&nbsp;
						<a href="?action=updateUser&id=<%= user.getId()%>">
							<button class='btn btn-info' data-toggle="modal" data-target="#myModal">Update</button>
						</a>         
                  		</td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<%@ include file="/WEB-INF/html/confirmationModal.html"%>
</body>
</html>