<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Admin Login Page</title>
<link rel="stylesheet" href="login_style.css">
</head>
<body>
	<main id="main">
		<h1 class="header-heading">Welcome</h1>
		<form action="AdminLoginDetails" method="post" id="form">
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