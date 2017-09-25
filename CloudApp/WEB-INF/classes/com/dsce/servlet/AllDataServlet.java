package com.dsce.servlet;

import java.io.IOException;
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

import com.dsce.daoimpl.DecryptionDAO;
import com.dsce.daoimpl.PolicyDAO;
import com.dsce.daoimpl.ResourceDAO;
import com.dsce.pojo.Patient;
import com.dsce.util.Constants;

public class AllDataServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {

		try {

			String email = (String) req.getSession().getAttribute(
					"loggedInUser");
			PolicyDAO policyDao = new PolicyDAO();
			int access = policyDao.getPrivileges(email,
					Constants.RESOURCE_PATIENT);
			List<Patient> decryptedPatientsList = new ArrayList<Patient>();
			ResourceDAO resourceDao = new ResourceDAO();
			List<Patient> patients = resourceDao.readPatient(null);
			for (Patient patient : patients) {

				DecryptionDAO decryptionDao = new DecryptionDAO();
				int encdecId = decryptionDao.getEncDecId(
						Integer.parseInt(patient.getId()),
						Constants.RESOURCE_PATIENT);

				String url = "http://localhost:8080/KDC/decrypt";
				HttpPost post = new HttpPost(url);

				HttpClient client = HttpClientBuilder.create().build();
				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				urlParameters.add(new BasicNameValuePair("enckeyid", String
						.valueOf(encdecId)));
				urlParameters.add(new BasicNameValuePair("name", patient
						.getName()));
				urlParameters.add(new BasicNameValuePair("gender", patient
						.getGender()));
				urlParameters.add(new BasicNameValuePair("mobile", patient
						.getMobile()));
				urlParameters.add(new BasicNameValuePair("age", patient
						.getAge()));
				urlParameters.add(new BasicNameValuePair("address", patient
						.getAddress()));

				post.setEntity(new UrlEncodedFormEntity(urlParameters));

				HttpResponse response = client.execute(post);

				Patient decryptedPatient = new Patient();
				decryptedPatient.setId(patient.getId());
				decryptedPatient.setName(response.getFirstHeader("name")
						.getValue());
				decryptedPatient.setGender(response.getFirstHeader("gender")
						.getValue());
				decryptedPatient.setMobile(response.getFirstHeader("mobile")
						.getValue());
				decryptedPatient.setAge(response.getFirstHeader("age")
						.getValue());
				decryptedPatient.setAddress(response.getFirstHeader("address")
						.getValue());
				decryptedPatientsList.add(decryptedPatient);
			}
			req.setAttribute("decryptedPatientsList", decryptedPatientsList);
			req.getRequestDispatcher("alldata.jsp").forward(req, resp);

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("alldata.jsp?error=true");

		}

	}

}
