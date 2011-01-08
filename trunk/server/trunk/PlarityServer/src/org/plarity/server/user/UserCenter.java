package org.plarity.server.user;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.plarity.server.database.IDatabase;
import org.plarity.server.util.Util;

public class UserCenter {

	private final static Logger LOGGER = Logger.getLogger(UserCenter.class.getName());

	private final IDatabase db;

	public UserCenter(IDatabase db) {
		this.db = db;
	}

	/**
	 * 
	 * @param email
	 * @return <b>true</b> if the user does not exist yet and everything went well.<br><b>false</b>otherwise!
	 */
	public boolean askForActivation(String email) {
		if (userExists(email)) {
			LOGGER.info("Sorry, user/email (" + email + ") already exists in system!");
			return false;
		}
		if (isPendingActivation(email)) {
			LOGGER.info("User/email (" + email + ") is already pending an activation, removing the current one!");
			removeActivation(email);
		}
		// user is not known to the system

		// -> create entry in pending activations database
		String stmt = "INSERT INTO `pend_activations` VALUES (?, ?);";
		try {
			PreparedStatement prep = db.prepareStatement(stmt);

			prep.setString(1, email);
			// -> create ID for activation
			String hash = Util.md5Sum(UUID.randomUUID().toString());
			prep.setString(2, hash);

			int res = prep.executeUpdate();
			if (res == 1) {
				return true;
			} else {
				System.err.println("Something went wrong while executing SQL statement: '" + stmt + "'");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Something went wrong while executing SQL statement: '" + stmt + "'", e);
		}
		return false;
	}

	/**
	 * 
	 * @param email
	 * @param name
	 * @return
	 */
	private boolean userExists(String email) {
		LOGGER.info("Checking if user exists: email='" + email + "'");
		try {
			//check if there exists a user with that email or name in database
			String stmt = "SELECT * FROM `user` WHERE `email`='" + email + "'";
			ResultSet rs = db.executeQuery(stmt);

			return rs.next();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Could not check if user exists: email='" + email + "'");
			return true;
		}
	}

	private boolean isPendingActivation(String email) {
		LOGGER.info("Checking if user is pending an activation: email='" + email + "'");
		try {
			//check if there exists a user with that email or name in database
			String stmt = "SELECT * FROM `pend_activation` WHERE `email`='" + email + "'";
			ResultSet rs = db.executeQuery(stmt);

			return rs.next();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Could not check if user is pending for activation: email='" + email + "'");
			return true;
		}
	}

	/**
	 * 
	 * @param email
	 * @param hash
	 * @return
	 */
	public boolean isValidActivation(String email, String hash) {
		LOGGER.info("Checking if user is pending an activation: email='" + email + "' hash='" + hash + "'");
		try {
			//check if there exists a user with that email or name in database
			String stmt = "SELECT * FROM `pend_activation` WHERE `email`='" + email + " AND `hash`='" + hash + "'";
			ResultSet rs = db.executeQuery(stmt);

			return rs.next();
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Could not check if user is pending for activation: email='" + email + "' hash='"
					+ hash + "'");
			return true;
		}
	}

	/**
	 * 
	 * @param email
	 * @return <b>The temporary password</b> if everything went well, <b>null</b> otherwise.
	 */
	public String initUser(String email) {
		String tmpPw = Util.generatePassword();
		String tmpPwHash = Util.md5Sum(tmpPw);

		// update DB
		String stmt = "INSERT INTO `user` VALUES (?, ?);";
		try {
			PreparedStatement prep = db.prepareStatement(stmt);

			prep.setString(1, email);
			prep.setString(2, tmpPwHash);

			int res = prep.executeUpdate();
			if (res == 1) {
				return tmpPw;
			} else {
				System.err.println("Something went wrong while executing SQL statement: '" + stmt + "'");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Something went wrong while executing SQL statement: '" + stmt + "'", e);
		}
		return null;
	}

	public boolean removeActivation(String email) {
		//remove activation and generate new one!
		try {
			//check if there exists a user with that email or name in database
			String stmt = "DELETE FROM `pend_activation` WHERE `email`='" + email + "'";
			db.executeQuery(stmt);
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.WARNING, "Could not delete current pending activation: email='" + email + "'");
			return false;
		}
	}

	public void updateUserProperty(String email, String property, String value) {
		//TODO
	}

	public String login(String email, String pwHash) {
		// TODO Auto-generated method stub
		return UUID.randomUUID().toString();
	}

	public boolean isLoggedIn(String userId, String sessionId) {
		// TODO Auto-generated method stub
		return true;
	}

}
