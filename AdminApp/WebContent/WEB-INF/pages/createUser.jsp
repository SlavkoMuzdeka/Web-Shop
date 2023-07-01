<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Create User</title>
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
				<form class="p-3 border" enctype="multipart/form-data"
					action="UserController?action=createUser" method="post">
					<div class="form-group">
						<input type="hidden" class="form-control" id="id" name="id">
					</div>
					<div class="form-group">
						<label for="firstName">First Name</label> <input type="text"
							class="form-control" id="firstName" name="firstName"
							placeholder="Enter the first name" required>
					</div>
					<div class="form-group">
						<label for="lastName">Last Name</label> <input type="text"
							class="form-control" id="lastName" name="lastName"
							placeholder="Enter the last name" required>
					</div>
					<div class="form-group">
						<label for="city">City</label> <input type="text"
							class="form-control" id="city" name="city"
							placeholder="Enter the city" required>
					</div>
					<div class="form-group">
						<label for="userName">Username</label> <input type="text"
							class="form-control" id="userName" name="userName"
							placeholder="Enter the username" required>
					</div>
					<div class="form-group">
						<label for="password">Password</label> <input type="text"
							class="form-control" id="password" name="password"
							placeholder="Enter the password" required>
					</div>
					<div class="form-group">
						<label for="avatar">Avatar</label> <input type="file"
							class="form-control-file" id="avatar" name="avatar" accept=".png">
					</div>
					<div class="form-group">
						<label for="mail">Email address</label> <input type="email"
							class="form-control" id="mail" name="mail"
							aria-describedby="emailHelp"
							placeholder="Enter the mail" required>
					</div>
					<div class="text-center">
						<button type="submit" class="btn btn-primary">Create</button>
					</div>
				</form>
			</div>
		</div>
	</div>
</body>
</html>