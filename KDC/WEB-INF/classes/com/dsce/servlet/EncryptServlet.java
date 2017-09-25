package com.dsce.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PublicKey;
import java.security.spec.RSAPublicKeySpec;
import java.util.Enumeration;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.dao.KDCDao;
import com.dsce.util.EncryptDecrypt;

public class EncryptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String enckeyId = req.getParameter("enckeyid");

		KDCDao dao = new KDCDao();
		try {
			Map<String, String> keys = dao.getKeys(enckeyId);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PublicKey pub = kf.generatePublic(new RSAPublicKeySpec(
					new BigInteger(keys.get("pubModulus")), new BigInteger(keys
							.get("pubexponent"))));
			Enumeration<String> names = req.getParameterNames();

			while (names.hasMoreElements()) {
				String paramName = names.nextElement();
				String paramValue = req.getParameter(paramName);
				if (!paramName.equals("enckeyid")) {
					byte[] encParamValue = EncryptDecrypt.encryptData(
							paramValue, pub);
					resp.setHeader(paramName, new BigInteger(encParamValue).toString());
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
