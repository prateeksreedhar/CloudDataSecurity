package com.dsce.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.UserDAO;

public class ApproveServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");

		UserDAO usersDao = new UserDAO();
		try {
			usersDao.approveUser(email);
			req.setAttribute("email", email);
			req.getRequestDispatcher("assignRole.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
