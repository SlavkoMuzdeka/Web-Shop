<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>

<%@ page import="org.unibl.etf.dto.Statistic"%>
<jsp:useBean id="statisticBean" type="org.unibl.etf.beans.StatisticBean"
	scope="session"></jsp:useBean>

<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Statistics</title>
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
			<h3>Logs</h3>
		</div>
	</div>
<div class="container-fluid">
	<div class="table-responsive"
		style="height: calc(100vh - 130px);">
		<table class="table table-dark table-hover table-striped"
			id="statisticsTable">
			<thead class="text-center">
				<tr>
					<th class="col-10">Description</th>
					<th class="col-2">Date and time</th>
				</tr>
			</thead>
			<tbody id="tableBodyStatistics" class="text-center">
				<%
				for (Statistic stat: statisticBean.readAll()) {
				%>
				<tr>
					<td style="word-wrap: break-word; max-width: 300px"><%=stat.getDescription()%></td>
					<td><%= stat.getDateTime() %></td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>
</div>
</body>
</html>