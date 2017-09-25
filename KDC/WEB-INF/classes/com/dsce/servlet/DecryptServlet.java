package com.dsce.servlet;

import java.io.IOException;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.PrivateKey;
import java.security.spec.RSAPrivateKeySpec;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dsce.dao.KDCDao;
import com.dsce.util.EncryptDecrypt;

public class DecryptServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		String enckeyId = req.getParameter("enckeyid");
		
		KDCDao dao = new KDCDao();
		try {
			Map<String, String> keys = dao.getKeys(enckeyId);
			KeyFactory kf = KeyFactory.getInstance("RSA");
			PrivateKey priv = kf.generatePrivate(new RSAPrivateKeySpec(
					new BigInteger(keys.get("privModulus")), new BigInteger(
							keys.get("privExponent"))));
			Enumeration<String> names = req.getParameterNames();
			Map<String, String> decMap = new HashMap<String, String>();
			while (names.hasMoreElements()) {
				String paramName = names.nextElement();
				String paramValue = req.getParameter(paramName);
				if (!paramName.equals("enckeyid")) {
					String decParamValue = EncryptDecrypt.decryptData(
							( new BigInteger(paramValue).toByteArray()), priv);
					decMap.put(paramName, new String(decParamValue));
					resp.setHeader(paramName, new String(decParamValue));
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
}
