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
			<table border="1px" width="100%">
				<tr style="background-color: skyBlue;">
					<th>ID</th>
					<th>First Name</th>
					<th>Last Name</th>
					<th>Login</th>
					<th>Date Of Birth</th>
				</tr>
				<%
					List list = (List) request.getAttribute("list");
				%>
				<%
					Iterator<UserBean> it = list.iterator();
				%>
				<%
					while (it.hasNext()) {
						UserBean bean = it.next();
				%>
				<tr align="center" style="background-color: #d3d3d3;">
					<td><%=bean.getId()%></td>
					<td><%=bean.getFirstName()%></td>
					<td><%=bean.getLastName()%></td>
					<td><%=bean.getLogin()%></td>
					<td><%=bean.getDob()%></td>
				</tr>
				<%
					}
				%>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>