<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%
		UserBean user= (UserBean) session.getAttribute("user");
	%>

	<a href="LoginClt">Login</a> |
	<a href="UserRegistrationClt">SignUp</a>

</body>
</html>