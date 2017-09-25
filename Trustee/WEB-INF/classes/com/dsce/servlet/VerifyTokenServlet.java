package com.dsce.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.dao.TokenDAO;

public class VerifyTokenServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String token = req.getParameter("token");
		String email = req.getParameter("email");
		System.out.println(email);
		System.out.println(token);
		try {
			TokenDAO dao = new TokenDAO();
			resp.setContentType("text/html");
			PrintWriter pw = resp.getWriter();

			if (dao.isValidToken(token, email)) {
				pw.write("true");
			} else {
				pw.write("false");
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

	}

}
