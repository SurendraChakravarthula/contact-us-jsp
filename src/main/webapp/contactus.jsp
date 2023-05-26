<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8" session="false"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Contact Us</title>
<link rel="stylesheet" href="contactus_style.css">
</head>
<body>
	<main id="body-container">
		<img src="./Images/developer.jpg" alt="">

		<form action="contactus" method="post" id="form">
			<h1 class="heading">Contact Us</h1>
			<label for="full_name">
				<h2>
					Full Name <span class="red"></span>
				</h2>
			</label> <input type="text" name="name" required> <label for="email">
				<h2>
					Email <span class="red"></span>
				</h2>
			</label> <input type="email" name="email" required> <label
				for="message">
				<h2>
					Message <span class="red">*</span>
				</h2>
			</label>
			<textarea name="message" id="message" required></textarea>
			<input type="submit">
		</form>
	</main>
</body>
</html>