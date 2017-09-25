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

public class UpdatePatientData extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		try {
			String id = req.getParameter("id");
			PolicyDAO policyDao = new PolicyDAO();
			int access = policyDao.getPrivileges((String) req.getSession()
					.getAttribute("loggedInUser"), Constants.RESOURCE_PATIENT);
			if (access >= 1) {
				DecryptionDAO decryptionDao = new DecryptionDAO();
				int encdecId = decryptionDao.getEncDecId(Integer.parseInt(id),
						Constants.RESOURCE_PATIENT);

				String name = req.getParameter("name");
				String gender = req.getParameter("gender");
				String mobile = req.getParameter("mobile");
				String age = req.getParameter("age");
				String address = req.getParameter("address");

				String url = "http://localhost:8080/KDC/encrypt";
				HttpPost post = new HttpPost(url);

				HttpClient client = HttpClientBuilder.create().build();

				List<NameValuePair> urlParameters = new ArrayList<NameValuePair>();
				urlParameters.add(new BasicNameValuePair("enckeyid", String
						.valueOf(encdecId)));
				urlParameters.add(new BasicNameValuePair("name", name));
				urlParameters.add(new BasicNameValuePair("gender", gender));
				urlParameters.add(new BasicNameValuePair("mobile", mobile));
				urlParameters.add(new BasicNameValuePair("age", age));
				urlParameters.add(new BasicNameValuePair("address", address));

				post.setEntity(new UrlEncodedFormEntity(urlParameters));

				HttpResponse response = client.execute(post);

				ResourceDAO dao = new ResourceDAO();
				Patient patient = new Patient();
				patient.setName(response.getFirstHeader("name").getValue());
				patient.setGender(response.getFirstHeader("gender").getValue());
				patient.setMobile(response.getFirstHeader("mobile").getValue());
				patient.setAge(response.getFirstHeader("age").getValue());
				patient.setAddress(response.getFirstHeader("address")
						.getValue());
				patient.setId(id);
				dao.updatePatient(patient);

				resp.sendRedirect("editPatient.jsp?update=done");
			
			} else {
				System.out.println("No rights ");
				resp.sendRedirect("editPatient.jsp?update=norights");
			}

		} catch (Exception e) {
			e.printStackTrace();
			resp.sendRedirect("editPatient.jsp?update=fail");
		}
	}
}
