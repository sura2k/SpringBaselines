<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>HOME</title>
</head>
<body>
	<h2>Welcome to IC (INDEPENDENT CONTRACTOR) Landing Page!</h2>
	<br/>
   	User Id: <c:out value="${sessionScope.user.userId}"/><br/>
   	First Name: <c:out value="${sessionScope.user.firstName}"/><br/>
   	Last Name: <c:out value="${sessionScope.user.lastName}"/><br/>
</body>
</html>