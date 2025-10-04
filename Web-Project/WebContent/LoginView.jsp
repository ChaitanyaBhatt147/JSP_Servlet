<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
	<%@ include file="Header.jsp"%>
	<%
		String errorMsg = (String) request.getAttribute("errorMsg");
	%>
	<%
		if (errorMsg != null) {
	%>
	<h2 style="color: red;"><%=errorMsg%></h2>
	<%
		}
	%>
	<div align="center">
		<h1>Login View</h1>
		<form action="LoginClt" method="post">
			<table>
				<tr>
					<th>Email</th>
					<td><input type="email" name="login" value=""
						placeholder="Enter Email"></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" value=""
						placeholder="Enter Password"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operations" value="Login">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>