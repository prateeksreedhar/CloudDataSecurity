package com.dsce.servlet;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.spec.RSAPrivateKeySpec;
import java.security.spec.RSAPublicKeySpec;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.message.BasicNameValuePair;

import com.dsce.dao.KDCDao;
import com.dsce.mail.SendMail;

public class RequestNewKeyServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost (HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		resp.setContentType("text/html");
		PrintWriter pw = resp.getWriter();

		String email = req.getParameter("email");
		String token = req.getParameter("token");

		String url = "http://localhost:8080/Trustee/verify";
		HttpPost post = new HttpPost(url);

		HttpClient client = HttpClientBuilder.create().build();

		List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
		urlParameters.add(new BasicNameValuePair("email", email));
		urlParameters.add(new BasicNameValuePair("token", token));

		post.setEntity(new UrlEncodedFormEntity(urlParameters));

		HttpResponse response = client.execute(post);
		System.out.println("Response Code : "
				+ response.getStatusLine().getStatusCode());

		BufferedReader rd = new BufferedReader(new InputStreamReader(response
				.getEntity().getContent()));

		StringBuffer result = new StringBuffer();
		String line = "";
		while ((line = rd.readLine()) != null) {
			result.append(line);
		}
		System.out.println(result);
		if (result.toString().contains("true")) {
			KeyPairGenerator keyPairGenerator;
			try {
				keyPairGenerator = KeyPairGenerator.getInstance("RSA");
				keyPairGenerator.initialize(2048); // 1024 used for normal
													// securities
				KeyPair keyPair = keyPairGenerator.generateKeyPair();
				PublicKey publicKey = keyPair.getPublic();
				PrivateKey privateKey = keyPair.getPrivate();

				KeyFactory kf = KeyFactory.getInstance("RSA");

				RSAPublicKeySpec rsaPubKeySpec = kf.getKeySpec(publicKey,
						RSAPublicKeySpec.class);
				BigInteger puMod = rsaPubKeySpec.getModulus();
				BigInteger puExp = rsaPubKeySpec.getPublicExponent();

				RSAPrivateKeySpec rsaPrivKeySpec = kf.getKeySpec(privateKey,
						RSAPrivateKeySpec.class);
				BigInteger prMod = rsaPrivKeySpec.getModulus();
				BigInteger prExp = rsaPrivKeySpec.getPrivateExponent();

				KDCDao dao = new KDCDao();
				int id = dao.insertKeys(puMod, puExp, prMod, prExp);

				SendMail mail = new SendMail();
				String body = "Hi <b>"
						+ email
						+ "</b><br> <br>New set of keys are granted to you and included in this email. You can use these keys only in the valid context <br><br>";
				body = body
						+ "<div style='padding: 5px; background-color: lightgray;'>Key Identifier : <b><i>"
						+ id + " </i></b></div>";
				mail.send(email,
						"AKLC | KDC | New keys are been generated to you", body);
				String url2 = "http://localhost:8080/Trsutee/mark";
				HttpPost post2 = new HttpPost(url2);

				HttpClient client2 = HttpClientBuilder.create().build();

				List<NameValuePair> urlParameters2 = new ArrayList<NameValuePair>();
				urlParameters2.add(new BasicNameValuePair("email", email));
				urlParameters2.add(new BasicNameValuePair("token", token));

				post2.setEntity(new UrlEncodedFormEntity(urlParameters2));

				client2.execute(post2);

				pw.print("New Set of Keys are been generated to you and their identification number alone have been sent in an email");

			} catch (Exception e) {
				e.printStackTrace();
			}

		} else {
			pw.print("<span style='color: red;'>Sorry, Your token is not a valid one</span>");
		}

		pw.close();

	}

}
