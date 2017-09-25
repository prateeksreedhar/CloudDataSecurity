package com.dsce.servlet;

import java.io.IOException;
import java.rmi.server.ServerCloneException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.UserDAO;
import com.dsce.pojo.User;

public class RegisterServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		User user = new User();
		user.setEmail(req.getParameter("email"));
		user.setPassword(req.getParameter("password"));
		user.setFirstName(req.getParameter("firstname"));
		user.setLastName(req.getParameter("lastname"));

		UserDAO dao = new UserDAO();
		try {
			dao.insertUser(user);
			resp.sendRedirect("register.jsp?register=done");
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("register.jsp?register=fail");
		}

	}

}
