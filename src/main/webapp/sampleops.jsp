<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html>
		<head>
  			<title>Sample Management</title>
  			<link rel="stylesheet" href="css/sample.css">
  			<link rel="icon" href="https://www.labvantage.com/wp-content/uploads/2023/01/LabVantage-favicon-144.png" sizes="192x192">
		</head>


	<body>

	<%-- When user is giving wrong credential , still session will generate with message parameter . So in this case there
	will be some not null value for the session ,, but we don't want to allow any user with wrong credential thats why we are
	adding one more condition that is user-name exist or not .. If along with session valid user-name also exist then only it
	will move forward to sampleops.jsp  --%>

	<%
		String username = "";
			if (session != null && session.getAttribute("username") != null)
			{
				username = (String) session.getAttribute("username");
			}

		else if (session == null || session.getAttribute("username") == null)
			{
				response.sendRedirect("index.jsp");
			}
	%>

		<header>
            <a href="sampleops.jsp" class="logo">
                <img src="https://www.labvantage.com/wp-content/uploads/2023/01/LabVantage_Logo-2X.png"
                     alt="LabVantage Logo">
                <h2>Sample Management</h2>
            </a>

            <nav>
                <a href="ViewSample"><button>View Samples</button></a>
                <a href="addsample.jsp"><button>Add Sample</button></a>
                <a href="<%= request.getContextPath() %>/UserLogout"><button>Logout</button></a>
            </nav>
        </header>



		<div class="center">

	  			<h2>Welcome <%= username %></h2>
	  			<div class="menu">
			  		<a href="addsample.jsp"><button>Add New Sample</button></a>
			    	<a href="ViewSample"><button>View All Samples</button></a>
			  <!--  <a href="<%= request.getContextPath() %>/UserLogout"><button>Logout</button></a> -->
	  			</div>
		</div>
	</body>
</html>