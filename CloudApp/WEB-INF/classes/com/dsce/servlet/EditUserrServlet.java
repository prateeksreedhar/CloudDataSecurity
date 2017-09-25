package com.dsce.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.PolicyDAO;
import com.dsce.daoimpl.UserDAO;
import com.dsce.pojo.User;

public class EditUserrServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
	
		
		String email = req.getParameter("email");
		UserDAO userDao = new UserDAO();
		PolicyDAO policyDao = new PolicyDAO();
		try {
			List<User> users = userDao.getAllUsers(email);
			User user = users.get(0);
			Map<String, Integer> policies = policyDao.getAllPrivileges(email);
			req.setAttribute("user", user);
			req.setAttribute("policies", policies);
			req.getRequestDispatcher("editUser.jsp").forward(req, resp);
			
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("editUser.jsp?error=true");
			
		}
	}

}
