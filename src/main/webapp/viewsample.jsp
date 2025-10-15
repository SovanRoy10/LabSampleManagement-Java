

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*" %>
<%@ page import="model.Sample" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="UTF-8">
  <title>View Samples</title>
  <link rel="stylesheet" href="css/sample.css">
  <link rel="icon" href="https://www.labvantage.com/wp-content/uploads/2023/01/LabVantage-favicon-144.png" sizes="192x192">
</head>
<body>

<%
  String alertMsg = (String) request.getAttribute("alertMsg");
  if (alertMsg != null) {
%>
  <script>alert("<%= alertMsg %>");</script>
<%
  }

  String username = "";
  if (session != null && session.getAttribute("username") != null) {
      username = (String) session.getAttribute("username");
  }

  if (session == null || session.getAttribute("username") == null) {
      response.sendRedirect("index.jsp");
      return;
  }
%>

<header>
  <a href="sampleops.jsp" class="logo">
    <img src="https://www.labvantage.com/wp-content/uploads/2023/01/LabVantage_Logo-2X.png"
         alt="LabVantage Logo">
    <h2>Sample Management</h2>
  </a>

  <nav>
    <h2><%= username %></h2>
    <a href="addsample.jsp"><button>Add Sample</button></a>
    <a href="<%= request.getContextPath() %>/UserLogout"><button>Logout</button></a>
  </nav>
</header>

<main>
  <h2>All Samples</h2>

  <div class="functionality">

    <!-- Combined Filter + Search Form -->
    <form class="form-filter" method="get" action="ViewSample">
      <label for="filterStatus">Filter by Status:</label>
      <select name="status" id="filterStatus">
        <option value="" <%= (request.getParameter("status") == null || request.getParameter("status").isEmpty()) ? "selected" : "" %>>All</option>
        <option value="Created" <%= "Created".equals(request.getParameter("status")) ? "selected" : "" %>>Created</option>
        <option value="In Progress" <%= "In Progress".equals(request.getParameter("status")) ? "selected" : "" %>>In Progress</option>
        <option value="Completed" <%= "Completed".equals(request.getParameter("status")) ? "selected" : "" %>>Completed</option>
      </select>

      <!-- Search input -->
      <input type="text" name="search" placeholder="Search samples..."
             value="<%= request.getParameter("search") != null ? request.getParameter("search") : "" %>">

      <button type="submit" class="search-btn">🔍</button>
    </form>

  </div>

  <!-- Samples Table -->
  <table border="1" id="samplesTable" class="samples-table">
    <thead>
      <tr>
        <th>ID</th>
        <th>Description</th>
        <th>Requested By</th>
        <th>Status</th>
        <th>Created Date</th>
        <th>Actions</th>
      </tr>
    </thead>
    <tbody>
      <%
        // Get the sample list from servlet ViewSample
        @SuppressWarnings("unchecked")
        List<Sample> samples = (List<Sample>) request.getAttribute("samples");

        String searchTerm = request.getParameter("search");
        String filterStatus = request.getParameter("status");

        List<Sample> filteredSamples = new ArrayList<>();

        if (samples != null) {
          for (Sample s : samples) {
            boolean matchesSearch = true;
            boolean matchesStatus = true;

            if (searchTerm != null && !searchTerm.trim().isEmpty()) {
              String lower = searchTerm.toLowerCase();
              matchesSearch = (
                s.getSampleId().toLowerCase().contains(lower) ||
                s.getDescription().toLowerCase().contains(lower) ||
                s.getRequestedBy().toLowerCase().contains(lower) ||
                s.getStatus().toLowerCase().contains(lower)
              );
            }

            if (filterStatus != null && !filterStatus.trim().isEmpty()) {
              matchesStatus = s.getStatus().equalsIgnoreCase(filterStatus);
            }

            if (matchesSearch && matchesStatus) {
              filteredSamples.add(s);
            }
          }
        }

        // Pagination setup
        int currentPage = 1;
        int recordsPerPage = 5;
        if (request.getParameter("page") != null) {
          currentPage = Integer.parseInt(request.getParameter("page"));
        }

        int totalRecords = filteredSamples.size();
        int start = (currentPage - 1) * recordsPerPage;
        int end = Math.min(start + recordsPerPage, totalRecords);

        if (filteredSamples != null && !filteredSamples.isEmpty()) {
          for (int i = start; i < end; i++) {
            Sample s = filteredSamples.get(i);
      %>
        <tr>
          <td><%= s.getSampleId() %></td>
          <td><%= s.getDescription() %></td>
          <td><%= s.getRequestedBy() %></td>
          <td><%= s.getStatus() %></td>
          <td><%= s.getCreatedDate() != null ? s.getCreatedDate().toString().split(" ")[0] : "" %></td>
          <td>
            <form class="table-form" action="<%= request.getContextPath() %>/updatesamplestatus.jsp?id=<%= s.getSampleId() %>" method="post">
              <button class="form-button-edit" type="submit">Edit</button>
            </form>

            <form class="table-form" action="<%= request.getContextPath() %>/DeleteSample?id=<%= s.getSampleId() %>" method="post">
              <button class="form-button-delete" type="submit">Delete</button>
            </form>
          </td>
        </tr>
      <%
          }
        } else {
      %>
        <tr>
          <td colspan="6" style="text-align:center;">No samples found</td>
        </tr>
      <%
        }
      %>
    </tbody>
  </table>

  <!-- Pagination Controls -->
  <%
    int totalPages = (int) Math.ceil(totalRecords * 1.0 / recordsPerPage);
    if (totalPages > 1) {
  %>
    <div class="pagination">
      <% if (currentPage > 1) { %>
        <a href="ViewSample?page=<%= currentPage - 1 %>&status=<%= filterStatus != null ? filterStatus : "" %>&search=<%= searchTerm != null ? searchTerm : "" %>">Previous</a>
      <% } else { %>
        <span class="disabled">Previous</span>
      <% } %>

      <span> Page <%= currentPage %> of <%= totalPages %></span>

      <% if (currentPage < totalPages) { %>
        <a href="ViewSample?page=<%= currentPage + 1 %>&status=<%= filterStatus != null ? filterStatus : "" %>&search=<%= searchTerm != null ? searchTerm : "" %>">Next</a>
      <% } else { %>
        <span class="disabled">Next</span>
      <% } %>
    </div>
  <%
    }
  %>

</main>
</body>
</html>
