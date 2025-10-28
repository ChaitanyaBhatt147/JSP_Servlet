package com.rays.util;

import java.text.SimpleDateFormat;
import java.util.Date;

import javax.servlet.http.HttpServletRequest;

public class DataValidator {
	public static boolean signUpValidation(HttpServletRequest request) {
		boolean isValid = true;
		if (request.getParameter("firstName") == "") {
			request.setAttribute("firstName", "First name is required.");
			isValid = false;
		}
		if (request.getParameter("lastName") == "") {
			request.setAttribute("lastName", "Last name is required.");
			isValid = false;
		}
		if (request.getParameter("login") == "") {
			request.setAttribute("login", "Email is required.");
			isValid = false;
		} else if (!request.getParameter("login").endsWith("^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$")) {
			request.setAttribute("login", "Invalid email. Plese enter a valid email.");
			isValid = false;
		}
		if (request.getParameter("password") == "") {
			request.setAttribute("password", "Password is required.");
			isValid = false;
		} else if (request
				.getParameter("password") != ("^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=!]).{8,12}$")) {
			request.setAttribute("password",
					"Password must be 8–12 characters long and include at least one uppercase letter, one lowercase letter, one digit, and one special character");
			isValid = false;
		}
		if (request.getParameter("dob") == "") {
			request.setAttribute("dob", "DOB is required.");
			isValid = false;
		} else if (!(request.getParameter("dob") == "")) {
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

			try {
				Date dob = sdf.parse(request.getParameter("dob"));
				Date now = new Date();
				int age = now.getYear() - dob.getYear();
				if (!(age >= 18 && age <= 60)) {
					request.setAttribute("dob", "you are not eligible for this web site");
					isValid = false;
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}
		return isValid;
	}

}
