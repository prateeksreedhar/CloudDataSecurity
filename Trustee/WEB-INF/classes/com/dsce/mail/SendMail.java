package com.dsce.mail;

import java.util.Properties;

import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.mail.Authenticator;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class SendMail {

	public void send(String to, String sub, String body) {
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class",
				"javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");

		Authenticator p = new SenderAuthentication();
		Session session = Session.getDefaultInstance(props, p);

		try {
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("testashok999@gmail.com",
					"DSCE | Trustee"));
			InternetAddress rcv = new InternetAddress(to);
			message.addRecipient(Message.RecipientType.TO, rcv);
			message.setSubject(sub);

			message.setContent(body, "text/html");

			Transport.send(message);

		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
