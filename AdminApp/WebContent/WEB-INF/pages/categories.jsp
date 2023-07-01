<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="org.unibl.etf.dto.Category"%>
<jsp:useBean id="categoryBean" type="org.unibl.etf.beans.CategoryBean"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<title>Categories</title>
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
		<div class="col-6">
			<h3>Categories</h3>
		</div>
		<div class="col-2 text-right">
			<button class="btn btn-success" data-toggle="modal"
				data-target="#categoryModal">Create New Category</button>
		</div>
	</div>
	<div class="container-fluid">
		<div class="table-container"
			style="height: calc(100vh - 130px); overflow-y: scroll;">
			<table class="table table-dark table-hover table-striped"
				id="categoriesTable">
				<thead class="text-center">
					<tr>
						<th class="col-4">Category name</th>
						<th class="col-4">Supercategory name</th>
						<th class="col-3">Action</th>
					</tr>
				</thead>
				<tbody id="tableBodyCategories" class="text-center">
					<%
					for (Category category : categoryBean.readAll()) {
					%>
					<tr>
						<td class="cell-ellipsis"><%=category.getName()%></td>
						<td>
							<%
							if (category.getSuperCategory() != null) {
								out.print(category.getSuperCategory().getName());
							} else {
								out.print("-");
							}
							%>
						</td>
						<td><a
							href="CategoryController?action=deleteCategory&id=<%=category.getId()%>">
								<button class='btn btn-primary'>
									<i class="fa fa-futboll-0">Delete</i>
								</button>
						</a></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
		</div>
	</div>
	<div class="modal" id="categoryModal">
		<div class="modal-dialog">
			<div class="modal-content">

				<div class="modal-header">
					<h4 class="modal-title">Add New Category</h4>
					<button type="button" class="close" data-dismiss="modal">&times;</button>
				</div>

				<div class="modal-body">
					<form action="CategoryController?action=createCategory"
						method="post">
						<div class="form-group">
							<label for="categoryName">Category Name:</label> <input
								type="text" class="form-control" id="categoryName"
								name="categoryName" autofocus="autofocus" required="required">
						</div>
						<div class="form-group form-check">
							<input type="checkbox" class="form-check-input"
								id="superCategoryCheckbox"> <label
								class="form-check-label" for="superCategoryCheckbox">Has
								Super Category</label>
						</div>
						<div id="superCategorySelect" style="display: none;">
							<div class="form-group">
								<label for="superCategory">Super Category:</label> <select
									class="form-control" id="superCategory" name="superCategory">
									<option value="">--Select--</option>
									<%
									for (Category category : categoryBean.readAll()) {
									%>
									<option value="<%=category.getId()%>"><%=category.getName()%></option>
									<%
									}
									%>
								</select>
							</div>
						</div>
						<div class="form-group">
							<label for="numAttributes">Number of Attributes:</label> <input
								type="number" class="form-control" id="numAttributes" min="1"
								name="numAttributes">
						</div>
						<div id="attributeFields"></div>
						<div class="modal-footer">
							<button type="button" class="btn btn-secondary"
								data-dismiss="modal">Close</button>
							<button type="button" class="btn btn-primary" id="nextButton">Next</button>
							<button type="submit" class="btn btn-success" id="submitButton" style="display:none;">Create</button>
						</div>
					</form>
				</div>
			</div>
		</div>
	</div>
</body>
</html>