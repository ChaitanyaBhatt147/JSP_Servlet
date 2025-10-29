<%@page import="com.rays.bean.OrderBean"%>
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
		<form action="OrderListCtl" method="post">
			<table>
				<tr>
					<th>Search</th>
					<td><input type="text" name="searchByShopeName" value=""
						placeholder="Search by Shope name"></td>
					<td><input type="text" name="searchByProductName" value=""
						placeholder="Search by Product name"></td>
					<td><input type="text" name="searchByPrice" value=""
						placeholder="Search by price"></td>
					<td><input type="date" name="searchByDop" value=""
						placeholder="Search by DOP"></td>
					<td><input type="submit" name="operation" value="search"></td>
				</tr>
			</table>
			<table border="1px" width="100%">
				<tr style="background-color: skyBlue;">
					<th>Delete</th>
					<th>ID</th>
					<th>Shop Name</th>
					<th>Product Name</th>
					<th>Price</th>
					<th>Date Of Purchase</th>
					<th>Edit</th>
				</tr>
				<%
					List list = (List) request.getAttribute("list");
					List nextList = (List) request.getAttribute("nextList");
					String successMsg = (String) request.getAttribute("successMsg");
					String errorMsg = (String) request.getAttribute("errorMsg");
					int pageNo = (int) request.getAttribute("pageNo");
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
					Iterator<OrderBean> it = list.iterator();
				%>
				<%
					while (it.hasNext()) {
						OrderBean bean = it.next();
				%>
				<tr align="center" style="background-color: #d3d3d3;">
					<td><input type="checkbox" name="ids" value=<%=bean.getId()%>></td>
					<td><%=bean.getId()%></td>
					<td><%=bean.getShopeName()%></td>
					<td><%=bean.getProdectName()%></td>
					<td><%=bean.getPrice()%></td>
					<td><%=bean.getDop()%></td>
					<td><a href="AddOrderCtl?id=<%=bean.getId()%>">edit</a></td>
				</tr>
				<%
					}
				%>
			</table>
			<table width="100%">
				<tr>
					<th></th>
					<td><input type="submit" name="operation" value="previous" <%=pageNo == 1 ? "disabled": ""%>></td>
					<td><input type="submit" name="operation" value="delete"></td>
					<td align="right"><input type="submit" name="operation"
						value="next" <%=nextList.size() == 0 ? "disabled": ""%>></td>
				</tr>
			</table>
			<input type="hidden" name="pageNo" value="<%=pageNo%>">
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>