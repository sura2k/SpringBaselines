<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Login</title>

 <link rel="stylesheet" href="static/main.css">

</head>
<body>
   <form method="POST" action="login">
      <table border="0">
      <tr>
      <td>Login</td>
      <td><input type="text" name="username"></td>
      </tr>
      <tr>
      <td>Password</td>
      <td><input type="password" name="password"></td>
      </tr>
      </table>
      <input type="submit" value="Login" class="test-btn">
      <br/>
      * If CSS class 'btn-test' has applied to the Login button, non-secured resource filtering is working fine!
   </form>
</body>
</html>