<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<title>Login</title>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js"></script>
<script src="pomScript.js"></script>
</head>
<body>
	<div
		class="container d-flex justify-content-center align-items-center vh-100">
		<div class="card col-md-4">
			<div class="card-body">
				<h3 class="card-title text-center mb-4">Login</h3>
				<form method="POST" action="UserController?action=login">
					<div class="mb-3">
						<label for="username" class="form-label">Username</label> <input
							type="text" class="form-control" id="username" name="username"
							placeholder="Enter username" autofocus="autofocus">
					</div>
					<div class="mb-3">
						<label for="password" class="form-label">Password</label> <input
							type="password" class="form-control" id="password"
							name="password" placeholder="Enter password">
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary" name="submit">Login</button>
					</div>
				</form>
			</div>
			<%
			if (session.getAttribute("error") != null) {
			%>
			<div class="alert alert-danger alert-dismissible">
				<a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
				<strong>Error!</strong> Invalid username or password.
			</div>
			<%
			}
			%>
		</div>
	</div>
</body>
</html>