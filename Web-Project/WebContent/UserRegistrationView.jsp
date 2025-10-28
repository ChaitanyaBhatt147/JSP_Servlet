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
	<div align="center">
		<h1>Registration Page</h1>
		<%
			String successMsg = (String) request.getAttribute("successMsg");
			String errorMsg = (String) request.getAttribute("errorMsg");
		%>
		<%
			if (successMsg != null) {
		%>
		<h3 style="color: green;"><%=successMsg%></h3>
		<%
			}
		%>

		<%
			if (errorMsg != null) {
		%>
		<h3 style="color: red;"><%=errorMsg%></h3>
		<%
			}
		%>

		<form action="UserRegistrationCtl" method="post">
			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="Enter First Name"><span style="color: red"><%=request.getAttribute("firstName") != null ? request.getAttribute("firstName") : ""%></span></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="Enter Last Name"><span style="color: red"><%=request.getAttribute("lastName") != null ? request.getAttribute("lastName") : ""%></span></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><input type="email" name="login" value=""
						placeholder="Enter Email"><span style="color: red"><%=request.getAttribute("login") != null ? request.getAttribute("login") : ""%></span></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password" value=""
						placeholder="Enter Password"> <span style="color: red"><%=request.getAttribute("password") != null ? request.getAttribute("password") : ""%></span></td>
				</tr>
				<tr>
					<th>DOB</th>
					<td><input type="date" name="dob" value=""><span
						style="color: red"><%=request.getAttribute("dob") != null ? request.getAttribute("dob") : ""%></span></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operations" value="Signup">
					</td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>