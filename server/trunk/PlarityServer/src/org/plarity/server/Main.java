package org.plarity.server;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.plarity.server.database.IDatabase;
import org.plarity.server.database.SQLite;
import org.plarity.server.user.UserCenter;

public class Main {

	private final static Logger LOGGER = Logger.getLogger(Main.class.getName());

	public static String dbName = "";

	public static String port = "";

	public static IDatabase database;
	public static UserCenter userCenter;

	public static void main(String[] args) throws IOException, SQLException {

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equalsIgnoreCase("-n")) {
				dbName = args[++i];
			} else if (arg.equalsIgnoreCase("-p")) {
				port = args[++i];
			}
		}

		if (dbName.equalsIgnoreCase("")) {
			LOGGER.log(Level.SEVERE, "Please provide a valid name for the database, using the -n <dbName> option!");
			System.exit(1);
		}

		if (port.equalsIgnoreCase("")) {
			LOGGER.log(Level.SEVERE, "Please provide a valid port for the REST server, using the -p <port> option!");
			System.exit(1);
		}

		//connecting to database
		database = new SQLite(dbName);
		if (!database.connect()) {
			LOGGER.log(Level.SEVERE, "Could not connect to database!");
			System.exit(2);
		}

		//setting up user center
		userCenter = new UserCenter(database);

		//creating RESTful endpoint
		final String baseUri = "http://localhost:" + port + "/";
		final Map<String, String> initParams = new HashMap<String, String>();
		initParams.put("com.sun.jersey.config.property.packages", "org.plarity.server.resource");
		SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);

		//press the 'any key' button to quit the program
		System.in.read();
		threadSelector.stopEndpoint();
		if (!database.disconnect()) {
			LOGGER.log(Level.SEVERE, "Could not disconnect from database! Exiting anyhow!");
			System.exit(3);
		}

		System.exit(0);
	}
}