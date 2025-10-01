package com.rays.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
@WebServlet("/FirstServelet")//WildCard Mapping
public class FirstServelet extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
		responce.sendRedirect("FirstView.jsp");
		System.out.println("In doGet()");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse responce) throws ServletException, IOException {
		System.out.println("In doPost()");
		System.out.println(request.getParameter("firstName"));
		System.out.println(request.getParameter("lastName"));
		System.out.println(request.getParameter("login"));
		System.out.println(request.getParameter("password"));
		System.out.println(request.getParameter("dob"));
	}	
}
