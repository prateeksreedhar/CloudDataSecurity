package com.dsce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class LoginServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String pwd = req.getParameter("pwd");
		if (pwd != null && pwd.equals("admin123")) {
			req.getSession().setAttribute("loggedIn", "yes");
			resp.sendRedirect("home.jsp");
		} else {
			resp.sendRedirect("login.jsp?error=true");
		}
	}

}
