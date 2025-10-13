package com.rays.controller;

import java.io.IOException;
import java.text.ParseException;
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
		int pageNo =1;
		int pageSize =5;
		try {
			List list = model.search(bean,pageNo,pageSize);
			List nextList = model.search(bean,pageNo+1,pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
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
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		int pageNo =1;
		int pageSize =5;

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
		if (request.getParameter("operation").equals("next")) {
			pageNo =(Integer) Integer.parseInt(request.getParameter("pageNo"));
			pageNo++;
		}
		if (request.getParameter("operation").equals("previous")) {
			pageNo =(Integer) Integer.parseInt(request.getParameter("pageNo"));
			pageNo--;
		}
		if (request.getParameter("operation").equals("search")) {
				bean.setFirstName(request.getParameter("searchByFirstName"));
				bean.setLastName(request.getParameter("searchByLastName"));
				bean.setLogin(request.getParameter("searchByLogin"));
				try {
					bean.setDob(sdf.parse(request.getParameter("searchByDob")));
				} catch (ParseException e1) {
					e1.printStackTrace();
				}
				try {
					model.search(bean,pageNo,pageSize);
				} catch (Exception e) {
					e.printStackTrace();
				}
		}
		
		try {
			List list = model.search(bean,pageNo,pageSize);
			List nextList = model.search(bean,pageNo+1,pageSize);
			request.setAttribute("list", list);
			request.setAttribute("nextList", nextList);
			request.setAttribute("pageNo", pageNo);
		} catch (Exception e) {
			e.printStackTrace();
		}
		RequestDispatcher rd = request.getRequestDispatcher("UserListView.jsp");
		rd.forward(request, response);
	}
}
