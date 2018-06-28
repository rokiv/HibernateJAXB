<!DOCTYPE html>
<html>
<head><title>Results</title>
<link href="./css/styles.css" 
      rel="stylesheet" type="text/css"/>
</head>
<body>
<table class="title">
  <tr><th>
    <% String title = (String)request.getAttribute("title"); %>
    <%=title%>
  </th></tr>
</table>
<p/>
<%
  String id = request.getParameter("id");
  if (request.getAttribute("user")!=null) {
%>
<ul>
  <li>ID: ${user.id}</li>
  <li>First name: ${user.firstName}</li>
  <li>Last name: ${user.lastName}</li>
</ul>
<%
  } else {
%>
  No user with ID = <%= id%>
<%
  }
%>
<br>
<a href="index.jsp">Home</a>
</body></html>