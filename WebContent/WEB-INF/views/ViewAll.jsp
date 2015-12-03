<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>View All</title>
<link rel="stylesheet" href="assets/css/bootstrap.min.css">
<link rel="stylesheet" href="assets/css/bootstrap-theme.min.css">
</head>
<body>
	<nav class="navbar navbar-default navbar-static-top">
	<div class="container-fluid">
		<div class="navbar-header">
			<a class="navbar-brand" href="/FormationJEE/index.html"> <img
				width="30" height="30" alt="Brand" src="assets/images/java_logo.png">
			</a>
			<p class="navbar-text">J2EE</p>
			<ul class="nav navbar-nav">
				<li class="active"><a href="/FormationJEE/index.html">Home</a></li>
				<li><a href="/FormationJEE/add_book.html">Add</a></li>
				<li><a href="ViewAll">View All</a></li>
			</ul>
		</div>
	</div>
	</nav>
	<div class="container">

		<table class="table table-hover">
			<thead>
				<tr>
					<th>#</th>
					<th>Name</th>
					<th>Author</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach var="book" items="${books}">
					<tr>
						<th scope="row">${book.id}</th>
						<td>${book.name}</td>
						<td>${book.author}</td>
					</tr>

				</c:forEach>
			</tbody>
		</table>
	</div>
	<script src="assets/js/bootstrap.min.js"></script>
</body>
</html>