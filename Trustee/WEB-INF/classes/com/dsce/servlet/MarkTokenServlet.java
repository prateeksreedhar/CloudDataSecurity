package com.dsce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.dao.TokenDAO;

public class MarkTokenServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String token = req.getParameter("token");
		try {
			TokenDAO dao = new TokenDAO();
			dao.markToken(email, token);
			resp.setContentType("text/html");
			PrintWriter pw = resp.getWriter();
			pw.print("DONE");
		} catch (Exception e) {
			throw new ServletException();
		}

	}

}
