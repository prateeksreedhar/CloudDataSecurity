package com.dsce.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.UserDAO;

public class GrantAccessServlet extends HttpServlet {

	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String role = req.getParameter("role");

		UserDAO dao = new UserDAO();
		try {
			dao.addRole(email, role);
		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
