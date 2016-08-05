<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>
</head>
<body>

    <form action="<c:url value='/login'/>" method="post">
      <table>
      	<tr>
      		<td>Username </td>
      		<td><input type="text" name="username" placeholder="Enter username"></td>
      	</tr>
      	<tr>
      		<td>Password </td>
      		<td><input type="password" name="password" placeholder="Enter password"></td>
      	</tr>
      	<tr>
      		<td colspan="2"><button type="submit">Login</button></td>
      	</tr>
      </table>
      <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
    </form>
    
</body>
</html>