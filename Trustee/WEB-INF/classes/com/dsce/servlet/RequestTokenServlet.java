package com.dsce.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Timestamp;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.dao.TokenDAO;
import com.dsce.pojo.Token;
import com.dsce.util.TokenGenerator;

public class RequestTokenServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {
			Token t = new Token();
			t.setEmail(req.getParameter("email"));
			t.setStatus("NEW");
			t.setRequestTime(new Timestamp(System.currentTimeMillis()));
			t.setToken(TokenGenerator.newToken());

			TokenDAO dao = new TokenDAO();
			dao.insert(t);

			resp.setContentType("text/html");
			PrintWriter pw = resp.getWriter();
			pw.print("SUCCESSFULLY GENERATED A TOKEN");
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
			throw new ServletException();
		}

	}

}
