package com.dsce.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.UserDAO;

public class LoginServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String pwd = req.getParameter("password");

		UserDAO dao = new UserDAO();
		try {
			if (dao.isValidUser(email, pwd)) {
				req.getSession().setAttribute("loggedInUser", email);
				String role = dao.getRole(email);
				req.getSession().setAttribute("loggedInRole",role);
				
				resp.sendRedirect("index.jsp");
			} else {
				resp.sendRedirect("login.jsp?valid=false");
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
