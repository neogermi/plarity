package org.plarity.server.mail;

import javax.mail.PasswordAuthentication;

public class Authenticator extends javax.mail.Authenticator {
	private final PasswordAuthentication authentication;

	public Authenticator(final String username, final String password) {
		authentication = new PasswordAuthentication(username, password);
	}

	@Override
	protected PasswordAuthentication getPasswordAuthentication() {
		return authentication;
	}
}