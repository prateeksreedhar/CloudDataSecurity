package com.dsce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.UserDAO;
import com.dsce.pojo.User;

public class AllUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		
		try {
			UserDAO userDao = new UserDAO();
			List<User> users = userDao.getAllUsers(null);
			req.setAttribute("users", users);
			req.getRequestDispatcher("allUsers.jsp").forward(req, resp);
		}
		catch (Exception e) {
			resp.sendRedirect("allUsers.jsp?error=true");
		}
		
	}

}
