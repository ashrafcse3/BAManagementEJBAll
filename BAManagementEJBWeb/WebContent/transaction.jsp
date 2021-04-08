<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<link rel="stylesheet" href="style.css">
<title>Customer | Transactions</title>
</head>
<body>
<div class="topnav">
	<a class="active" href="#home">Home</a>
	<a href="CustomerServlet?action=logout">Logout</a>
</div>
<div class="loginForm">
<h1>Transactions for customer</h1>
<form action="TransactionDetailsServlet" method="post">
  <label>Transaction type:</label>
  <select name="transactionTypes">
    <c:forEach items="${tTypes}" var="tType" varStatus="status">
    	<option id="${status.index}" value=<c:out value="${status.index}"/>><c:out value="${tType}"/></option>
	</c:forEach>
  </select><br>
  <label>Amount:</label>
  <input type="text" name="amount" required><br>
  <label>Debit Account number:</label>
  <input type="text" name="frombankaccountnumber" required><br>
  <label>Credit Account number:</label>
  <input type="text" name="tobankaccountnumber" required><br>
  <input type="submit" value="transactionSubmit" name="transactionSubmit">
</form>
</div> 

</body>
</html>