package com.rays.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/LoginClt")
public class LogiClt extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String op = request.getParameter("operation");
		System.out.println("op -----> "+op);
		if (op != null) {
			HttpSession session = request.getSession();
			session.invalidate();
			request.setAttribute("successMsg", "User Logout Successfully");
		}
		RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
		rd.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		UserBean bean = new UserBean();
		UserModel model = new UserModel();
		HttpSession session = request.getSession();
		
		String email = request.getParameter("login");
		String password = request.getParameter("password");
		try {
			bean = model.authenticator(email, password);
			if (bean != null) {
				System.out.println("Login Successfull");
				session.setAttribute("user", bean);	
				response.sendRedirect("WelcomeView.jsp");
			} else {
				request.setAttribute("errorMsg", "Invalid login or password");
				RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
				rd.forward(request, response);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
