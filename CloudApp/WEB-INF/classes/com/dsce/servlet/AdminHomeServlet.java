package com.dsce.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.UserDAO;
import com.dsce.pojo.User;

public class AdminHomeServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		UserDAO userdao = new UserDAO();
		try {
			List<User> newUsers = userdao.getNewUsers();
			req.setAttribute("newUsers", newUsers);
			req.getRequestDispatcher("adminHome.jsp").forward(req, resp);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	
	}
}
