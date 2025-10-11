<%@page import="java.util.Iterator"%>
<%@page import="java.util.List"%>
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
		<h3>User List</h3>
		<form action="UserListCtl" method="post">
			<table>
				<tr>
					<th>Search</th>
					<td><input type="text" name="searchByFirstName" value=""
						placeholder="Search by First name"></td>
					<td><input type="text" name="searchByLastName" value=""
						placeholder="Search by Last name"></td>
					<td><input type="text" name="searchByLogin" value=""
						placeholder="Search by Login"></td>
					<td><input type="text" name="searchByDob" value=""
						placeholder="Search by DOB"></td>
					<td><input type="submit" name="operation" value="search"></td>
				</tr>
			</table>
			<table border="1px" width="100%">
				<tr style="background-color: skyBlue;">
					<th>Delete</th>
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login</th>
					<th>Date Of Birth</th>
					<th>Edit</th>
				</tr>
				<%
					List list = (List) request.getAttribute("list");
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
				<%
					Iterator<UserBean> it = list.iterator();
				%>
				<%
					while (it.hasNext()) {
						UserBean bean = it.next();
				%>
				<tr align="center" style="background-color: #d3d3d3;">
					<td><input type="checkbox" name="ids" value=<%=bean.getId()%>></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getDob()%></td>
					<td><a href="UserCtl?id=<%=bean.getId()%>">edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table>
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="delete"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>