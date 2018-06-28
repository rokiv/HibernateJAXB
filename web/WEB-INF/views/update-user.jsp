<%--
  Created by IntelliJ IDEA.
  User: AOne
  Date: 6/19/2018
  Time: 11:00 AM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Update user</title>
</head>
<body>
<h3>Update user</h3>
<form method="post" action="/update-user">
    <table border="0">
        <tr>
            <td>Enter user ID</td>
            <td><input type="text" name="id"/></td>
        </tr>
        <tr>
            <td>New first name</td>
            <td><input type="text" name="firstName"/></td>
        </tr>
        <tr>
            <td>New last name</td>
            <td><input type="text" name="lastName"/></td>
        </tr>
    </table>
    <input type="submit" value="Update user"/>
</form>
</body>
</html>
