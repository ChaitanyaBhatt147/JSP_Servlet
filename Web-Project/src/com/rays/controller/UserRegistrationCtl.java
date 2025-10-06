package com.rays.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;


@WebServlet("/UserRegistrationCtl")
public class UserRegistrationCtl extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.sendRedirect("UserRegistrationView.jsp");
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		try {
			bean.setFirstName(request.getParameter("firstName"));
			bean.setLastName(request.getParameter("lastName"));
			bean.setLogin(request.getParameter("login"));
			bean.setPassword(request.getParameter("password"));
			bean.setDob(sdf.parse(request.getParameter("dob")));
			
			model.add(bean);
			
			request.setAttribute("successMsg", "User added Succrssfully");
			
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());			
		}
		
		RequestDispatcher rd = request.getRequestDispatcher("UserRegistrationView.jsp");
		rd.forward(request, response);
	}

}
