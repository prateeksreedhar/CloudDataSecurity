package com.dsce.servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.dao.TokenDAO;
import com.dsce.pojo.Token;

public class TokenServlet extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			TokenDAO dao = new TokenDAO();
			List<Token> tokens = dao.getAllTokens();
			req.setAttribute("tokens", tokens);
			req.getRequestDispatcher("tokens.jsp").forward(req, resp);
		} catch (Exception e) {
			e.printStackTrace();

		}
	}

}
