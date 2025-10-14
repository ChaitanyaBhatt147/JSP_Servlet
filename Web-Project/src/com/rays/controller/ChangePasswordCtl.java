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


@WebServlet("/ChangePasswordCtl.do")
public class ChangePasswordCtl extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.sendRedirect("ChangePasswordView.jsp");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		HttpSession session = request.getSession();

		UserBean user = (UserBean) session.getAttribute("user");
		String oldPassword = request.getParameter("oldPassword");
		String newPassword = request.getParameter("newPassword");
		String conformPassword = request.getParameter("confirmPassword");
		if (user == null) {
			if (session != null) {
				session.invalidate();
			}
			request.setAttribute("errorMsg", "Sessoin Expired. Please Login.");
			RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
			rd.forward(request, response);
			
		} else {
			if (!newPassword.equals(conformPassword)) {
				request.setAttribute("errorMsg", "New Password and confirm Password both are different");
				
			} else {
				try {
					model.changePassword(user.getLogin(), oldPassword, newPassword);
					request.setAttribute("successMsg", "Password changed successfully");
				} catch (Exception e) {
					request.setAttribute("errorMsg", e.getMessage());
				}
				RequestDispatcher rd = request.getRequestDispatcher("ChangePasswordView.jsp");
				rd.forward(request, response);
			}
		}

	}

}
