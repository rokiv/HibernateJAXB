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
    <title>Add user</title>
</head>
<body>
<h3>Add user</h3>
<form method="post" action="/add-user">
    <table border="0">
        <tr>
            <td>First name</td>
            <td><input type="text" name="firstName"/></td>
        </tr>
        <tr>
            <td>Last name</td>
            <td><input type="text" name="lastName"/></td>
        </tr>
    </table>
    <input type="submit" value="Add new user"/>
</form>
</body>
</html>
