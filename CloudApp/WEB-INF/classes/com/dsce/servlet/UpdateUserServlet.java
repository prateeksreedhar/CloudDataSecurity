package com.dsce.servlet;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.PolicyDAO;
import com.dsce.daoimpl.UserDAO;
import com.dsce.util.Constants;

public class UpdateUserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = req.getParameter("email");
		String role = req.getParameter("role");
		
		UserDAO userdao = new UserDAO();
		PolicyDAO policyDao = new PolicyDAO();
		Map<String, Integer> accessMap = new HashMap<String, Integer>();
		accessMap.put(Constants.RESOURCE_LABREPORT, Integer.parseInt(req.getParameter(Constants.RESOURCE_LABREPORT)));
		accessMap.put(Constants.RESOURCE_PATIENT, Integer.parseInt(req.getParameter(Constants.RESOURCE_PATIENT)));
		accessMap.put(Constants.RESOURCE_PRESCRIPTION, Integer.parseInt(req.getParameter(Constants.RESOURCE_PRESCRIPTION)));

		try {
			userdao.addRole(email, role);
			policyDao.updateAccess(email, accessMap);
			resp.sendRedirect("editUser.jsp?edit=done");
		} catch (SQLException e) {
			e.printStackTrace();
			resp.sendRedirect("editUser.jsp?edit=fail");
		}
	
	}

}
