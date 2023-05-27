<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Messages</title>
<link rel="stylesheet" href="dashboard.css">
<body>
	<%
	response.setHeader("Cache-Control", "no-cache, no-store, must-revalidate");

	String username = (String) session.getAttribute("username");
	if (username == null) {
		response.sendRedirect("login.jsp");
	}
	%>
	<header id="header">
		<div class="header-container">
			<div class="button">
				<a href="logout">Logout</a>
			</div>
		</div>
	</header>
	<main id="main">
		<table class="active">
			<caption>
				<h1>Active Requests</h1>
			</caption>
			<tbody>
				<tr>
					<th class="table-header">id</th>
					<th class="table-header">Name</th>
					<th class="table-header">Email</th>
					<th class="table-header">Message</th>
					<th class="table-header">Timestamp</th>
					<th class="table-header">Archive</th>
				</tr>
				<c:forEach var="request" items="${activeRequests}">
					<tr class="display-row">
						<td td class="table-content">${request.getId()}</td>
						<td td class="table-content">${request.getName()}</td>
						<td td class="table-content">${request.getEmail()}</td>
						<td td class="table-content">${request.getMessage()}</td>
						<td td class="table-content">${request.getTimestamp()}</td>
						<td td class="table-content">
							<form action="dashboard" method="post" class="link">
								<button type="submit" name="id" value="${request.id}">Archive</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<table class="archive">
			<caption>
				<h1>Archived Requests</h1>
			</caption>
			<tbody>
				<tr>
					<th class="table-header">id</th>
					<th class="table-header">Name</th>
					<th class="table-header">Email</th>
					<th class="table-header">Message</th>
					<th class="table-header">Timestamp</th>
					<th class="table-header">Unarchive</th>
				</tr>
				<c:forEach var="request" items="${archiveRequests}">
					<tr class="display-row">
						<td class="table-content">${request.getId()}</td>
						<td class="table-content">${request.getName()}</td>
						<td class="table-content">${request.getEmail()}</td>
						<td class="table-content">${request.getMessage()}</td>
						<td class="table-content">${request.getTimestamp()}</td>
						<td class="table-content">
							<form action="dashboard" method="post" class="link">
								<button type="submit" name="id" value="${request.id}">Unarchive</button>
							</form>
						</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
	</main>
</body>
</html>