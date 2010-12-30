package org.plarity.server.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.plarity.server.database.IDatabase;
import org.plarity.server.mail.Mail;
import org.plarity.server.util.Util;

public class UserCenter {

	private final static Logger LOGGER = Logger.getLogger(UserCenter.class.getName());

	private final IDatabase db;
	private final Mail mail;

	public UserCenter(IDatabase db, Mail mail) {
		this.db = db;
		this.mail = mail;
	}

	/**
	 * 
	 * @param email
	 * @param name
	 * @return <b>true</b> if the user does not exist yet and everything went well.<br><b>false</b>otherwise!
	 */
	public boolean registerUser(String email, String name) {
		if (userExists(email, name)) {
			LOGGER.info("Sorry, user/email already exists in system: email='" + email + "', name='" + name + "'");
			return false;
		} else {
			// user is not known in the system
			// -> create temporary password
			String pw = Util.generatePassword();
			String pwHash = Util.md5Sum(pw);

			// -> register user in db
			try {
				PreparedStatement prep = db.prepareStatement("INSERT INTO `user` VALUES (?, ?, ?);");

				prep.setString(1, email);
				prep.setString(2, name);
				prep.setString(3, pwHash);

				int res = prep.executeUpdate();
				if (res == 1) {
					// -> send email for authentification
					//TODO: send email to user with temp pw!
					sendActivationEmail(email, name);
					return true;
				} else {
					System.err.println("Something went wrong while executing SQL statement: '" + prep.toString() + "'");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	/**
	 * 
	 * @param email
	 * @param name
	 * @return
	 */
	private boolean userExists(String email, String name) {
		LOGGER.info("Checking if user exists: email='" + email + "', name='" + name + "'");
		try {
			//check if there exists a user with that email or name in database
			String stmt = "SELECT * FROM `user` WHERE `email`='" + email + "' OR `name`='" + name + "'";
			ResultSet rs = db.executeQuery(stmt);

			return rs.next();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Could not check if user exists: email='" + email + "', name='" + name + "'");
			return true;
		}
	}

	private boolean sendActivationEmail(String email, String name) {
		//TODO: meaningful message!
		return mail.sendMail(email, "Activate your plarity account", "TODO");

	}

}
