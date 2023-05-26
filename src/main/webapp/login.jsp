<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login Page</title>
<link rel="stylesheet" href="login_style.css">
</head>
<body>
	<c:if test="${not empty requestScope.error}">
		<div
			style="color: red; font-size: 20px; text-align: center; border: 1px solid #888; width: fit-content; margin: auto">Invalid
			username or password</div>
	</c:if>
	<main id="main">
		<h1 class="header-heading">Welcome</h1>
		<form action="login" method="post" id="form">
			<label for="full_name">
				<h2>User Name</h2>
			</label> <input type="text" name="username" required> <label
				for="password">
				<h2>Password</h2>
			</label> <input type="password" name="password"> <input
				class="button" type="submit" value="Login" required>
		</form>
	</main>
</body>
</html>