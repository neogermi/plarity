package org.plarity.server.database;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public interface IDatabase {

	public boolean connect();

	public boolean disconnect();

	public PreparedStatement prepareStatement(String sql) throws SQLException;

	public ResultSet executeQuery(String query) throws SQLException;

}
