<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Home</title>
</head>
<body>

	<p>
      Logged in as <b><c:out value="${pageContext.request.remoteUser}"/></b><br>
      With grant type <b><sec:authentication property="principal.authorities" /></b>
    </p>
    
    <br/>
    <br/>
    <form name="logout" action="<c:url value='/logout'/>" method="post">
         <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}" />
         <input type="submit" value="Logout">
    </form>
</body>
</html>