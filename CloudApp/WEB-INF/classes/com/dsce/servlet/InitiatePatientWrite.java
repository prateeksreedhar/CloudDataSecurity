package com.dsce.servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.daoimpl.PolicyDAO;
import com.dsce.util.Constants;

public class InitiatePatientWrite extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String email = (String) req.getSession().getAttribute("loggedInUser");
		PolicyDAO policyDao = new PolicyDAO();
		try {
			int access = policyDao.getPrivileges(email,
					Constants.RESOURCE_PATIENT);
			if (access >= 2) {
				req.setAttribute("access", "yes");
			} else {
				req.setAttribute("access", "no");
			}
			req.getRequestDispatcher("newPatient.jsp").forward(req, resp);

		} catch (SQLException e) {
			e.printStackTrace();
		}

	}

}
