<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Form</title>
</head>
<body>
	<div align="center">
		<form action="GetForm.jsp">
			<table>
				<tr>
					<th>First Name</th>
					<td><input type="text" name="firstName" value=""
						placeholder="Enter First Name"></td>
				</tr>
				<tr>
					<th>Last Name</th>
					<td><input type="text" name="lastName" value=""
						placeholder="Enter Last Name"></td>
				</tr>
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
					<th>DOB</th>
					<td><input type="date" name="dob" value=""></td>
				</tr>
				<tr>
					<th></th>
					<td> <input type = "submit" name = "operations" value = "Save"> </td>
				</tr>
			</table>
		</form>
	</div>
</body>
</html>