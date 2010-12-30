package org.plarity.server.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;

import org.plarity.server.util.Util;

public class UserCenter extends Thread {

	private Connection conn;
	private final String dbServer;
	private final String dbName;
	private final String dbUser;
	private final String dbPass;

	public UserCenter(String dbServer, String dbName, String dbUser, String dbPass) {
		this.dbServer = dbServer;
		this.dbName = dbName;
		this.dbUser = dbUser;
		this.dbPass = dbPass;
	}

	@Override
	public void run() {
		System.out.println("Starting database access!");
		try {
			System.out.println(dbServer + " " + dbName + " " + dbUser + " " + dbPass);
			conn = DriverManager.getConnection("jdbc:mysql://" + dbServer + "/" + dbName, dbUser, dbPass);
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!!Database ready to roll!!!!!!!!!!!!!!!!!!!!!!!!!!!");
	}

	public boolean registerUser(String email, String name) {
		if (userExists(email, name)) {
			return false;
		} else {
			// user is not known in the system
			// -> create temporary password
			// -> register user in db

			String pw = Util.generatePassword();
			String pwHash = Util.md5Sum(pw);

			try {
				String stmt = "INSERT INTO user (email, name, pw_hash) VALUES (" + email + ", " + name + ", " + pwHash
						+ ")";
				boolean res = conn.createStatement().execute(stmt);
				if (res) {
					//TODO: send email to user with temp pw!
				} else {
					System.err.println("Something went wrong while executing SQL statement: '" + stmt + "'");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			return false;
		}
	}

	private boolean userExists(String email, String name) {
		String stmt = "SELECT `id` FROM `user` WHERE `email`='" + email + "' OR `name`='" + name + "'";
		try {
			//check if there exists a user with that email or name in database
			ResultSet rs = conn.createStatement().executeQuery(stmt);
			return !rs.first();
		} catch (SQLException e) {
			System.err.println("ERROR: While executing SQL statement: '" + stmt + "'");
			e.printStackTrace();
			return false;
		}
	}

}
