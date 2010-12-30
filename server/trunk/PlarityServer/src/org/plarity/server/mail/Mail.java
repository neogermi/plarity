package org.plarity.server.mail;

import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.mail.Message;
import javax.mail.Message.RecipientType;
import javax.mail.MessagingException;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Mail {

	private final static Logger LOGGER = Logger.getLogger(Mail.class.getName());

	private final String host;
	private final String port;
	private final String address;
	private final String username;
	private final String password;

	public Mail(String host, String port, String address, String username, String password) {
		this.host = host;
		this.port = port;
		this.address = address;
		this.username = username;
		this.password = password;
	}

	private Session getSession() {
		Authenticator authenticator = new org.plarity.server.mail.Authenticator(username, password);

		Properties properties = new Properties();
		properties.setProperty("mail.smtp.submitter", authenticator.getPasswordAuthentication().getUserName());
		properties.setProperty("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		properties.setProperty("mail.smtp.ssl", "true");
		properties.setProperty("mail.smtp.auth", "true");

		properties.setProperty("mail.smtp.host", host);
		properties.setProperty("mail.smtp.port", port);

		return Session.getInstance(properties, authenticator);
	}

	public boolean sendMail(String to, String subject, String message) {
		Session session = getSession();
		try {
			Message mail = new MimeMessage(getSession());

			mail.addRecipient(RecipientType.TO, new InternetAddress(to));
			mail.addFrom(new InternetAddress[] { new InternetAddress(address) });

			mail.setSubject(subject);
			mail.setContent(message, "text/plain");

			Transport.send(mail);
			LOGGER.info("Successfully sent email to '" + to + "', subject: '" + subject + "'");
			return true;
		} catch (MessagingException mex) {
			LOGGER.log(Level.WARNING, "Sending email failed, exception: " + mex);
		}
		LOGGER.log(Level.WARNING, "Could not send email.");
		return false;
	}

}
