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
		<%
			String successMsg = (String) request.getAttribute("successMsg");
			String errorMsg = (String) request.getAttribute("errorMsg");
			UserBean bean = (UserBean) request.getAttribute("bean");
		%>
		<%
			if (bean != null && bean.getId() > 0) {
		%>
		<h3>Update User</h3>
		<%
			} else {
		%>
		<h3>Add User</h3>
		<%
			}
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

		<form action="UserCtl.do" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="id"
						value="<%=bean != null ? bean.getId() : ""%>"></td>
				</tr>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName"
						value="<%=bean != null ? bean.getFirstName() : ""%>"
						placeholder="Enter First Name"><span style="color: red"><%=request.getAttribute("firstName") != null ? request.getAttribute("firstName") : ""%></span></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName"
						value="<%=bean != null ? bean.getLastName() : ""%>"
						placeholder="Enter Last Name"><span style="color: red"><%=request.getAttribute("lastName") != null ? request.getAttribute("lastName") : ""%></span></td>
				</tr>
				<tr>
					<th>Email</th>
					<td><input type="email" name="login"
						value="<%=bean != null ? bean.getLogin() : ""%>"
						placeholder="Enter Email"><span style="color: red"><%=request.getAttribute("login") != null ? request.getAttribute("login") : ""%></span></td>
				</tr>
				<tr>
					<th>Password</th>
					<td><input type="password" name="password"
						value="<%=bean != null ? bean.getPassword() : ""%>"
						placeholder="Enter Password"><span style="color: red"><%=request.getAttribute("password") != null ? request.getAttribute("password") : ""%></span></td>
				</tr>
				<tr>
					<th>DOB</th>
					<td><input type="date" name="dob"
						value="<%=bean != null ? bean.getDob() : ""%>"><span
						style="color: red"><%=request.getAttribute("dob") != null ? request.getAttribute("dob") : ""%></span></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operations"
						value="<%=bean != null ? "Update" : "Add User"%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>