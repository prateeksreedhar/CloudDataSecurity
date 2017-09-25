package com.dsce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class AdminloginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String password = req.getParameter("password");
		System.out.println("Admin login called .. :) "+password);
		if (password != null && password.equals("cloud999")) {
			req.getSession().setAttribute("loggedInUser", "Admin");
			req.getSession().setAttribute("loggedInRole", "Admin");
			resp.sendRedirect("adminHome");
		} else {
			resp.sendRedirect("admin.jsp?valid=false");
		}

	}

}
