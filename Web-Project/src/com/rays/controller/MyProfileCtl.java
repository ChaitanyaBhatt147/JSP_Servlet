package com.rays.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/MyProfileCtl")
public class MyProfileCtl extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		UserBean user = (UserBean) session.getAttribute("user");
		if (user == null) {
			if (session != null) {
				session.invalidate();
			}
			request.setAttribute("errorMsg", "Sessoin Expired. Please Login.");
			RequestDispatcher rd = request.getRequestDispatcher("LoginView.jsp");
			rd.forward(request, response);

		} else {
			request.setAttribute("bean", user);
			RequestDispatcher rd = request.getRequestDispatcher("MyProfileView.jsp");
			rd.forward(request, response);
		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		HttpSession session = request.getSession();
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		UserBean bean = new UserBean();
		UserModel model = new UserModel();

		try {
			bean.setFirstName(request.getParameter("firstName"));
			bean.setLastName(request.getParameter("lastName"));
			bean.setLogin(request.getParameter("login"));
			bean.setPassword(request.getParameter("password"));
			bean.setDob(sdf.parse(request.getParameter("dob")));
			bean.setId(Integer.parseInt(request.getParameter("id")));
			model.Update(bean);
			request.setAttribute("successMsg", "User updated Succrssfully");
		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
			e.printStackTrace();
		}
		session.setAttribute("user", bean);
		RequestDispatcher rd = request.getRequestDispatcher("MyProfileView.jsp");
		rd.forward(request, response);
	}
}
