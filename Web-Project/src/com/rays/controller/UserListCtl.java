package com.rays.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.UserBean;
import com.rays.model.UserModel;

@WebServlet("/UserListCtl")
public class UserListCtl extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		try {
			List list = model.search(bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		UserModel model = new UserModel();
		UserBean bean = new UserBean();
		String[] ids = request.getParameterValues("ids");
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");

		if (request.getParameter("operation").equals("delete")) {
			if (ids != null && ids.length > 0) {
				for (String id : ids) {
					try {
						model.Delete(Integer.parseInt(id));
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				request.setAttribute("successMsg", "Record deleted successfully");
			} else {
				request.setAttribute("errorMsg", "Plesae select at least one record");
			}
		}
		if (request.getParameter("operation").equals("search")) {
			if (request.getParameter("searchByFirstName")!= null) {
				bean.setFirstName(request.getParameter("searchByFirstName"));
				try {
					model.search(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("searchByLastName")!= null) {
				bean.setLastName(request.getParameter("searchByLastName"));
				try {
					model.search(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("searchByLogin")!= null) {
				bean.setLogin(request.getParameter("searchByLogin"));
				try {
					model.search(bean);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
			if (request.getParameter("searchByDob")!= null) {
				try {
					bean.setDob(sdf.parse(request.getParameter("searchByFirstName")));
					model.search(bean);
				}
				catch (Exception e) {
					e.printStackTrace();
				}
			}
		}
		try {
			List list = model.search(bean);
			request.setAttribute("list", list);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		rd.forward(request, response);
	}
}
