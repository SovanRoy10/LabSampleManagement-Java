<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html >
		<head>
  			<title>Sample Management</title>
  			<link rel="stylesheet" href="css/sample.css">
  			<link rel="icon" href="https://www.labvantage.com/wp-content/uploads/2023/01/LabVantage-favicon-144.png" sizes="192x192">
		</head>

<body>
	<%
	
		String username = "";
		if (session != null && session.getAttribute("username") != null) 
		{
		username = (String) session.getAttribute("username");
		}	
		
		
		if (session == null || session.getAttribute("username") == null) 
		{
			response.sendRedirect("index.jsp");
		}
			
	%>
		
		<!--
		<header>
		  <nav>
			    <a href="sampleops.jsp">Home</a>
			    <a href="ViewSample">View Samples</a>
			    <a href="<%= request.getContextPath() %>/UserLogout">Logout</a>
		  </nav>
		</header>
		-->

		<header>
                    <a href="sampleops.jsp" class="logo">
                        <img src="https://www.labvantage.com/wp-content/uploads/2023/01/LabVantage_Logo-2X.png"
                             alt="LabVantage Logo">
                        <h2>Sample Management</h2>
                    </a>

                    <nav>
                        <h2><%= username %></h2>
                        <a href="ViewSample"><button>View Samples</button></a>
                        <a href="<%= request.getContextPath() %>/UserLogout"><button>Logout</button></a>
                    </nav>
        </header>

<main>
  <h2>Add New Sample</h2>
  
  
  	<form id="sampleForm"  method="post" onsubmit="return handleAddSample(event)" class="sample-form">
		    
		    <input type="text" id="sampleid" name="sampleid" placeholder="Enter Sample ID" required>
			<input type="text" id="description" name="description" placeholder="Enter Sample Description" required>
			<input type="text" id="requestedBy" name="requestedBy" placeholder="Enter Requester Name" required value ="<%=username %>">


			<select id="status" name="status" required>
			  <option value="" disabled selected>Select status</option>
			  <option value="Created">Created</option>
			  <option value="In Progress">In Progress</option>
			  <option value="Completed">Completed</option>
			</select>

    		<button type="submit">Add Sample</button>
  	</form>
</main>

		<script src="js/app.js"></script>

</body>
</html>