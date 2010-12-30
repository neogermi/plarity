package org.plarity.server.user;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:test.db");
			Statement stat = conn.createStatement();
			stat.executeUpdate("DROP TABLE IF EXISTS `user`");
			stat.executeUpdate("CREATE TABLE `user` (email TEXT, name TEXT, pw_hash TEXT);");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		System.out.println("Database ready!");
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
				PreparedStatement prep = conn.prepareStatement("INSERT INTO `user` VALUES (?, ?, ?);");

				prep.setString(1, email);
				prep.setString(2, name);
				prep.setString(3, pwHash);

				int res = prep.executeUpdate();
				if (res == 1) {
					//TODO: send email to user with temp pw!
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

	private boolean userExists(String email, String name) {
		String stmt = "SELECT * FROM `user` WHERE `email`='" + email + "' OR `name`='" + name + "'";
		try {
			//check if there exists a user with that email or name in database
			ResultSet rs = conn.createStatement().executeQuery(stmt);

			return rs.next();
		} catch (SQLException e) {
			System.err.println("ERROR: While executing SQL statement: '" + stmt + "'");
			e.printStackTrace();
			return false;
		}
	}

}
