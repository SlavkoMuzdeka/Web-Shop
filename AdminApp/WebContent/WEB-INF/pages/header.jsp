<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html lang="en">
<head>
<meta charset="utf-8">
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js">
</script>
<script src="js/pomScript.js"></script>
</head>
<body>
	<nav class="navbar navbar-expand-lg navbar-dark bg-dark">
		<a class="navbar-brand">Admin App</a>
		<ul class="nav nav-tabs">
			<li class="nav-item">
				<a class="nav-link" href="UserController?action=users" id="users-link">Users</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="CategoryController?action=categories" id="categories-link">Categories</a>
			</li>
			<li class="nav-item">
				<a class="nav-link" href="StatisticController?action=statistics" id="statistics-link">Logs</a>
			</li>
		</ul>
		<a class="btn btn-danger ml-auto" href="?action=logout">Logout</a>
	</nav>
</body>
</html>