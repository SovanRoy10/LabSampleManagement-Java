<%@ page language="java" contentType="text/html; charset=UTF-8"

    pageEncoding="UTF-8"%>

<!DOCTYPE html>

<html>

<head>

    <title>Register</title>

    <link rel="stylesheet" href="css/register.css">

</head>

<body>

		<%	
		String message = "";
		
		if(session !=null && session.getAttribute("message") != null)
			{
				message = (String)session.getAttribute("message");
			}
		session.removeAttribute("message");
		session.invalidate();	
		%>

    <div class="form-card">

        <h2>Register</h2>
        <p class = "error"> <%=message %> </p>

        <form action="<%=request.getContextPath()%>/UserRegistration" method="post">

            <input type="text" name="username" placeholder="Enter Username" required><br>

            <input type="password" name="password" placeholder="Enter Password" required><br>

            <input type="email" name="email" placeholder="Enter Email" title="Porper Email Format Required" required><br>

            <input type="text" name="phone" placeholder="Enter Phone Number" pattern="[0-9]{10}"

                   title="Enter a valid 10-digit phone number" required><br>

            

            <input type="submit" value="Register">

        </form>

        <p>

            Already have an account? 

            <a href="<%=request.getContextPath()%>/index.jsp">Login here</a>

        </p>

    </div>

</body>

</html>