package com.rays.controller;

import java.io.IOException;
import java.text.SimpleDateFormat;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.rays.bean.OrderBean;
import com.rays.model.OrderModel;
import com.rays.util.DataValidator;


@WebServlet("/AddOrderCtl")
public class AddOrderCtl extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String id = request.getParameter("id");
		OrderModel model = new OrderModel();
		OrderBean bean = new OrderBean();
		if (id != null) {
			try {
				bean = model.findById(Integer.parseInt(id));
				request.setAttribute("bean", bean);
			} catch (Exception e) {
				request.setAttribute("errorMsg", e.getMessage());
				e.printStackTrace();
			}
		}
		RequestDispatcher rd = request.getRequestDispatcher("AddOrder.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		OrderBean bean = new OrderBean();
		OrderModel model = new OrderModel();
		String op = request.getParameter("operations");
		System.out.println(op);
		try {
			bean.setShopeName(request.getParameter("shopeName"));
			bean.setProdectName(request.getParameter("productName"));
			bean.setPrice(Double.parseDouble(request.getParameter("price")));
			bean.setDop(sdf.parse(request.getParameter("dop")));
			if (op.equals("Update")) {
				System.out.println("data updated");
				bean.setId(Integer.parseInt(request.getParameter("id")));
				model.Update(bean);
				request.setAttribute("successMsg", "Order updated Succrssfully");
			} else {
				System.out.println("data added");
				model.add(bean);
				request.setAttribute("successMsg", "Order added Succrssfully");
			}


		} catch (Exception e) {
			request.setAttribute("errorMsg", e.getMessage());
		}

		RequestDispatcher rd = request.getRequestDispatcher("AddOrder.jsp");
		rd.forward(request, response);
	}

}
