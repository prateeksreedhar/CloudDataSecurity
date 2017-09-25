package com.dsce.servlet;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.dao.TokenDAO;
import com.dsce.mail.SendMail;

public class GrantTokenServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		String email = req.getParameter("email");
		String action = req.getParameter("action");
		TokenDAO dao = new TokenDAO();

		if (action.equals("approve")) {
			try {
				String token = dao.grantToken(email);
				String body = "<div style='font-family: verdana;' ><b>Hi "
						+ email
						+ ", </b> <br> <br>Your request for a new token has been<span style='color: green;'><b> APPROVED</b></span> by the Admin and here is your token: <br><br> <i>"
						+ token
						+ "</i> <br><br>--<br>Regards<br>AKLC<br>www.vtuprojects.com</div>";
				String sub = "AKLC : Your request for new Token has been Approved";
				SendMail mail = new SendMail();
				mail.send(email, sub, body);
			} catch (Exception e) {
				e.printStackTrace();
			}
		} else {
			try {
				dao.rejectToken(email);
				String body = "<div style='font-family: verdana;' ><b>Hi "
						+ email
						+ ", </b> <br> <br>Sorry, your request for a new token has been<span style='color: red;'><b> REJECTED</b></span> by the Admin. <br><br>--<br>Regards<br>AKLC<br>www.vtuprojects.com</div>";
				String sub = "AKLC : Your request for new Token has been Rejected";
				SendMail mail = new SendMail();
				mail.send(email, sub, body);
			} catch (Exception e) {
				e.printStackTrace();
			}
		}

	}

}
