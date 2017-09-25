package com.dsce.servlet;

import java.io.IOException;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.PolicyDAO;

public class AccessPolicyServlet extends HttpServlet{
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		PolicyDAO policyDao = new PolicyDAO();
		try {
			Map<String, Integer> result = policyDao.getAllPrivileges(email);
			req.setAttribute("policies", result);
			req.getRequestDispatcher("showPolicies.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("showPolicies.jsp?error=true");
		}
	
	}

}
