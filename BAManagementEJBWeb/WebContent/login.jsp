<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Login | Customer</title>
</head>
<body>
<div class="loginForm">
<h1>Login for customer</h1>
<form action="CustomerServlet" method="post">
  <label>User Name:</label>
  <input type="text" name="userName" required><br>
  <label>Password:</label>
  <input type="password" name="password" required><br>
  <input type="submit" value="login">
</form>
</div> 
</body>
</html>