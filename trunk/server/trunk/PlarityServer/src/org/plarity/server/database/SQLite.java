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
			/* util tables */
			stat.executeUpdate("DROP TABLE IF EXISTS `i18n`");
			stat.executeUpdate("CREATE TABLE `i18n` (trans TEXT);");
			stat.executeUpdate("INSERT INTO `i18n`(rowid, trans) VALUES (1, '...');");
			stat.executeUpdate("DROP TABLE IF EXISTS `currency`");
			stat.executeUpdate("CREATE TABLE `currency` (name TEXT, html_symbol TEXT);");
			stat.executeUpdate("INSERT INTO `currency`(rowid, name, html_symbol) VALUES (1, 'Euro', '&#8364;');");
			stat.executeUpdate("INSERT INTO `currency`(rowid, name, html_symbol) VALUES (2, 'Dollar', '&36;');");
			/* user tables */
			stat.executeUpdate("DROP TABLE IF EXISTS `user`");
			stat.executeUpdate("CREATE TABLE `user` (email TEXT, pw_hash TEXT);");
			stat.executeUpdate("INSERT INTO `user`(rowid, email, pw_hash) VALUES (1, 'neogermi@googlemail.com', 'password');"); /* DEBUG */
			stat.executeUpdate("INSERT INTO `user`(rowid, email, pw_hash) VALUES (2, 'neogermi2@googlemail.com', 'password');"); /* DEBUG */
			stat.executeUpdate("DROP TABLE IF EXISTS `pend_activation`");
			stat.executeUpdate("CREATE TABLE `pend_activation` (email TEXT, hash TEXT);");
			stat.executeUpdate("DROP TABLE IF EXISTS `user_session`");
			stat.executeUpdate("CREATE TABLE `user_session` (user_id INTEGEER, session_id INTEGER);");
			/* charity tables */
			stat.executeUpdate("DROP TABLE IF EXISTS `charity`");
			stat.executeUpdate("CREATE TABLE `charity` (name TEXT);");
			stat.executeUpdate("INSERT INTO `charity`(rowid, name) VALUES (1, 'Kinder in Not');"); /* DEBUG */
			stat.executeUpdate("INSERT INTO `charity`(rowid, name) VALUES (2, 'SOS Kinderdorf');"); /* DEBUG */
			/* pledge tables */
			stat.executeUpdate("DROP TABLE IF EXISTS `pledge`");
			stat.executeUpdate("CREATE TABLE `pledge` (user_id INTEGER, pledge_text TEXT, start_date TEXT, end_date TEXT, penalty_amount INTEGER, currency_id INTEGER, charity_id INTEGER);");
			stat.executeUpdate("INSERT INTO `pledge`(user_id, pledge_text, start_date, end_date, penalty_amount, currency_id, charity_id) VALUES (1, 'not smoke', 1294483197, 1296540000, 12, 1, 2);"); /* DEBUG */
			stat.executeUpdate("INSERT INTO `pledge`(user_id, pledge_text, start_date, end_date, penalty_amount, currency_id, charity_id) VALUES (2, 'quit smoking', 1294483197, 1296540000, 12, 2, 1);"); /* DEBUG */
			stat.executeUpdate("DROP TABLE IF EXISTS `pledge_history`");
			stat.executeUpdate("CREATE TABLE `pledge_history` (user_id INTEGER, pledge TEXT, start_date INTEGER, end_date INTEGER, penalty_amount INTEGER, charity_id INTEGER, status INTEGER);");
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
