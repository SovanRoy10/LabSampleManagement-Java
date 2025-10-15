<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="dao.SampleDao, model.Sample" %>

<%

	Sample sample = null;

	if (session == null || session.getAttribute("username") == null) 
	{
		response.sendRedirect("index.jsp");
	}

	else
	{
    String s_id = request.getParameter("id");
    
    SampleDao dao = new SampleDao();

    sample = dao.getSampleById(s_id);
	}

	String username = "";
    if (session != null && session.getAttribute("username") != null)
    {
    	username = (String) session.getAttribute("username");
    }

%> 
    
    
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Update Sample Status</title>
<link rel="stylesheet" href="css/sample.css">
<link rel="icon" href="https://www.labvantage.com/wp-content/uploads/2023/01/LabVantage-favicon-144.png" sizes="192x192">
</head>
<body>
        <!--
		<header>
  			<h1>Sample Management</h1>
  				<nav>
    				<a href="sampleops.jsp">Home</a>
    				<a href="ViewSample">View Sample</a>
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
                                <a href="ViewSample"><button>View Sample</button></a>
                                <a href="<%= request.getContextPath() %>/UserLogout"><button>Logout</button></a>
                            </nav>
        </header>



	<form action="UpdateSampleStatus" method="post" class="sample-form">
		    
		    <input type="text" name = "sampleid" value ="<%=sample.getSampleId() %>" readonly style ="cursor:not-allowed">
			<input type="text" value ="<%=sample.getDescription() %>" readonly style ="cursor:not-allowed">
			<input type="text" value ="<%=sample.getRequestedBy() %>" readonly style ="cursor:not-allowed">


			<select id="status" name="status" required>
			  <option value="" disabled selected>Select status</option>
			  <option value="Created">Created</option>
			  <option value="In Progress">In Progress</option>
			  <option value="Completed">Completed</option>
			</select>

    		<button type="submit">Update Sample</button>
  	</form>

</body>
</html>