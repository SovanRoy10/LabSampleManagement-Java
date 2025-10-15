<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

<title>Login</title>

<link rel="stylesheet" href="css/styles.css">

</head>

<body>

	<%
	String message = "";
	if (session != null && session.getAttribute("message") != null)
	{
		message = (String) session.getAttribute("message");
	}
	session.removeAttribute("message");
	session.invalidate();
	%>

	<div class="form-card">

		<h2>Login</h2>

		<p class = "error"> <%=message %> </p>

		<form action="<%=request.getContextPath()%>/UserLogin" method="post">

				<input type="email" name="email" placeholder="Enter Email Id" required><br>

				<input type="password" name="password" placeholder="Enter Password" required><br>

				<input type="submit" value="Login">

		</form>

		<p>

			New user? <a href="<%=request.getContextPath()%>/register.jsp">Register
			here</a>

		</p>

	</div>

</body>

</html>