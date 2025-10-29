<%@page import="com.rays.bean.OrderBean"%>
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
			OrderBean bean = (OrderBean) request.getAttribute("bean");
			
		%>
		<%
			if (bean != null && bean.getId() > 0) {
		%>
		<h3>Update Order</h3>
		<%
			} else {
		%>
		<h3>Add Order</h3>
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

		<form action="AddOrderCtl" method="post">
			<table>
				<tr>
					<td><input type="hidden" name="id"
						value="<%=bean != null ? bean.getId() : ""%>"></td>
				</tr>
				<tr>
					<th>Shop Name</th>
					<td><input type="text" name="shopeName"
						value="<%=bean != null ? bean.getShopeName() : ""%>"
						placeholder="Enter Shope Name"><span style="color: red"><%=request.getAttribute("shopeName") != null ? request.getAttribute("shopeName") : ""%></span></td>
				</tr>
				<tr>
					<th>Product Name</th>
					<td><input type="text" name="productName"
						value="<%=bean != null ? bean.getProdectName() : ""%>"
						placeholder="Enter Product Name"><span style="color: red"><%=request.getAttribute("productName") != null ? request.getAttribute("productName") : ""%></span></td>
				</tr>
				<tr>
					<th>Price</th>
					<td><input type="text" name="price"
						value="<%=bean != null ? bean.getPrice() : ""%>"
						placeholder="Enter Price"><span style="color: red"><%=request.getAttribute("price") != null ? request.getAttribute("price") : ""%></span></td>
				</tr>
				<tr>
					<th>DOP</th>
					<td><input type="date" name="dop"
						value="<%=bean != null ? bean.getDop() : ""%>"><span
						style="color: red"><%=request.getAttribute("dop") != null ? request.getAttribute("dop") : ""%></span></td>
				</tr>
				<tr>
					<th></th>
					<td><input type="submit" name="operations"
						value="<%=bean != null ? "Update" : "Add Order"%>"></td>
				</tr>
			</table>
		</form>
	</div>
	<%@ include file="Footer.jsp"%>
</body>
</html>