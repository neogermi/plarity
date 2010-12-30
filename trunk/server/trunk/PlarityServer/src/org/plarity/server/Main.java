package org.plarity.server;

import com.sun.grizzly.http.SelectorThread;
import com.sun.jersey.api.container.grizzly.GrizzlyWebContainerFactory;

import java.io.IOException;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

import org.plarity.server.user.UserCenter;

public class Main {

	public static String dbServer = "";
	public static String dbName = "";
	public static String dbUser = "";
	public static String dbPassword = "";

	public static String port = "";

	public static UserCenter userCenter;

	public static void main(String[] args) throws IOException, SQLException {

		for (int i = 0; i < args.length; i++) {
			String arg = args[i];
			if (arg.equalsIgnoreCase("-n")) {
				dbName = args[++i];
			} else if (arg.equalsIgnoreCase("-s")) {
				dbServer = args[++i];
			} else if (arg.equalsIgnoreCase("-u")) {
				dbUser = args[++i];
			} else if (arg.equalsIgnoreCase("-pw")) {
				dbPassword = args[++i];
			} else if (arg.equalsIgnoreCase("-p")) {
				port = args[++i];
			}
		}

		userCenter = new UserCenter(dbServer, dbName, dbUser, dbPassword);
		userCenter.start();

		final String baseUri = "http://localhost:" + port + "/";
		final Map<String, String> initParams = new HashMap<String, String>();

		initParams.put("com.sun.jersey.config.property.packages", "org.plarity.server.resource");

		System.out.println("Starting grizzly...");
		SelectorThread threadSelector = GrizzlyWebContainerFactory.create(baseUri, initParams);
		System.out.println(String.format("Jersey app started with WADL available at %sapplication.wadl\n"
				+ "Try out %shelloworld\nHit enter to stop it...", baseUri, baseUri));
		System.in.read();
		threadSelector.stopEndpoint();
		System.exit(0);
	}
}