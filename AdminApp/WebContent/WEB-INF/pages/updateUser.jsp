<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<jsp:useBean id="userBean" type="org.unibl.etf.beans.UserBean"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Update user</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
</head>
<body>
	<div class="container">
		<div class="row justify-content-center align-items-center vh-100">
			<div class="col-md-6">
				<form class="p-3 border"
					enctype="multipart/form-data"
					action="UserController?action=updateUser" method="post">
					<div class="form-group">
						<input type="hidden" class="form-control" id="id" name="id"
							value="<%=userBean.getUser().getId()%>">
					</div>
					<div class="form-group">
						<label for="firstName">First Name</label> <input type="text"
							class="form-control" id="firstName" name="firstName"
							value="<%=userBean.getUser().getFirstName()%>">
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label> <input type="text"
							class="form-control" id="lastName" name="lastName"
							value="<%=userBean.getUser().getLastName()%>">
					</div>
					<div class="form-group">
						<label for="city">City</label> <input type="text"
							class="form-control" id="city" name="city"
							value="<%=userBean.getUser().getCity()%>">
					</div>
					<div class="form-group">
						<label for="userName">Username</label> <input type="text"
							class="form-control" id="userName" name="userName"
							value="<%=userBean.getUser().getUserName()%>">
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="text"
							class="form-control" id="password" name="password"
							value="<%=userBean.getUser().getPassword()%>">
					</div>
					<div class="form-group">
						<label for="avatar">Avatar</label> <input type="file"
							class="form-control-file" id="avatar" name="avatar" accept=".png">
					</div>
					<div class="form-group">
						<label for="mail">Email address</label> <input type="email"
							class="form-control" id="mail" name="mail"
							aria-describedby="emailHelp"
							value="<%=userBean.getUser().getMail()%>">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Update</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>