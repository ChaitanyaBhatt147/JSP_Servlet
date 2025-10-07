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
	<div align="Center">
		<h3>Change Password</h3>
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
		<form action="ChangePasswordCtl" method="post">
			<table>
				<tr>
					<th>Old Password</th>
					<td><input type="password" name="oldPassword" value=""
						placeholder="Enter Password"></td>
				</tr>
				<tr>
					<th>New Password</th>
					<td><input type="password" name="newPassword" value=""
						placeholder="Enter Password"></td>
				</tr>
				<tr>
					<th>Confirm Password</th>
					<td><input type="password" name="confirmPassword" value=""
						placeholder="Enter Password"></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operations"
						value="Change Password"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>