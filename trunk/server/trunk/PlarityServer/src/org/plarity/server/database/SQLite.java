package org.plarity.server.database;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SQLite implements IDatabase {

	private final static Logger LOGGER = Logger.getLogger(SQLite.class.getName());

	private final String dbName;
	private Connection conn;

	public SQLite(String dbName) {
		this.dbName = dbName;
	}

	@Override
	public boolean connect() {
		LOGGER.info("Set up database access for SQLite db!");
		try {
			Class.forName("org.sqlite.JDBC");
			conn = DriverManager.getConnection("jdbc:sqlite:" + dbName);
			Statement stat = conn.createStatement();
			stat.executeUpdate("DROP TABLE IF EXISTS `user`");
			stat.executeUpdate("CREATE TABLE `user` (email TEXT, name TEXT, pw_hash TEXT);");
			LOGGER.info("Database ready to be used!");
			return true;
		} catch (Exception e) {
			LOGGER.log(Level.SEVERE, "Could not initialize SQLite DB!", e);
		}
		return false;
	}

	@Override
	public boolean disconnect() {
		try {
			LOGGER.info("Disconnecting SQLite DB!");
			conn.close();
			LOGGER.info("SQLite DB disconnected and closed!");
			return true;
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Could not disconnect SQLite DB!", e);
		}
		return false;
	}

	@Override
	public ResultSet executeQuery(String query) throws SQLException {
		return conn.createStatement().executeQuery(query);
	}

	@Override
	public PreparedStatement prepareStatement(String sql) throws SQLException {
		return conn.prepareStatement(sql);
	}

}
