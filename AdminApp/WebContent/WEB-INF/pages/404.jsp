<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Not Found</title>
<link rel="stylesheet"
	href="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/css/bootstrap.min.css">
<script
	src="https://cdn.jsdelivr.net/npm/jquery@3.6.3/dist/jquery.slim.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js"></script>
<script
	src="https://cdn.jsdelivr.net/npm/bootstrap@4.6.2/dist/js/bootstrap.bundle.min.js">
</script>
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-12">
				<div class="error-template">
					<h1 class="error-code">404</h1>
					<h2 class="error-message">Page not found</h2>
					<div class="error-description">Sorry, the page you are
						looking for could not be found. Please check the URL and try
						again.</div>
					<a href="UserController?action=users" class="btn btn-primary back-link"><i
						class="fas fa-home"></i> Return to homepage</a>
				</div>
			</div>
		</div>
	</div>
</body>
</html>