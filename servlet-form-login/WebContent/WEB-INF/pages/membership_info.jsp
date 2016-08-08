<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Membership Info</title>
</head>
<body>
	Hello <c:out value="${sessionScope.user.firstName}"/>,<br/>
	<c:choose>
    	<c:when test="${'PENDING'==sessionScope.user.membership}">
        	Your membership has not approved yet.
        	<br />
    	</c:when>    
    	<c:when test="${'SUSPENDED'==sessionScope.user.membership}">
        	Your membership has been suspended.
        	<br />
    	</c:when>
    	<c:otherwise>
        	Your membership has been approved.
        	<br />
    	</c:otherwise>
	</c:choose>
</body>
</html>