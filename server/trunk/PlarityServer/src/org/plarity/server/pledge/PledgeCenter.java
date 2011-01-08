package org.plarity.server.pledge;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.plarity.server.database.IDatabase;

public class PledgeCenter {

	private final static Logger LOGGER = Logger.getLogger(PledgeCenter.class.getName());

	private final IDatabase db;

	public PledgeCenter(IDatabase db) {
		this.db = db;
	}

	public String createPledge(String userId, String pledgeText, String endDate, String penaltyAmount,
			String currencyId, String charityId) {

		// TODO: validate values
		// TODO: return new resource id

		// create entry in database
		String stmt = "INSERT INTO `pledge`(user_id, pledge_text, start_date, end_date, penalty_amount, currency_id, charity_id) "
				+ "VALUES (?, ?, ?, ?, ?, ?, ?);";
		try {
			PreparedStatement prep = db.prepareStatement(stmt);

			prep.setInt(1, Integer.parseInt(userId));
			prep.setString(2, pledgeText);
			prep.setString(3, Integer.valueOf((int) (System.currentTimeMillis() / 1000)).toString()); /*TODO: bad code */
			prep.setString(4, endDate);
			prep.setInt(5, Integer.parseInt(userId));
			prep.setInt(6, Integer.parseInt(userId));
			prep.setInt(7, Integer.parseInt(userId));

			//TODO: how to get the generated id from the SQL query??

			int res = prep.executeUpdate();
			if (res == 1) {
				return "GENERATED_KEY";
			} else {
				System.err.println("Something went wrong while executing SQL statement: '" + stmt + "'");
			}
		} catch (SQLException e) {
			LOGGER.log(Level.SEVERE, "Something went wrong while executing SQL statement: '" + stmt + "'", e);
		}
		return null;
	}
}
