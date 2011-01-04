package org.plarity.server.resource;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.codehaus.jettison.json.JSONException;
import org.codehaus.jettison.json.JSONObject;
import org.plarity.server.Main;

@Path("/")
public class PlarityServerResource {

	private final static Logger LOGGER = Logger.getLogger(PlarityServerResource.class.getName());

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	@Path("/register")
	public Response registerUser(String content) {
		try {
			JSONObject data = new JSONObject(content);
			String email = data.getString("email");

			if (Main.userCenter.askForActivation(email)) {
				//send activation email!
				Main.mail.sendActivationEmail(email);
				return Response.ok().build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		} catch (JSONException e) {
			String warning = "Could not register user, not enough data provided OR wrong JSON format: '" + content
					+ "'";
			LOGGER.log(Level.WARNING, warning);
			return Response.status(Status.NOT_ACCEPTABLE).entity(warning).build();
		} catch (Throwable t) {
			t.printStackTrace();
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(t.getLocalizedMessage()).build();
		}
	}

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	@Path("/activate")
	public Response activateUser(String content) {
		try {
			JSONObject data = new JSONObject(content);
			String email = data.getString("email");
			String hash = data.getString("hash");

			if (Main.userCenter.isValidActivation(email, hash)) {
				if (!Main.userCenter.removeActivation(email)) {
					LOGGER.log(Level.WARNING, "Could not remove activation of user('" + email + "'). Please check!");
				}
				String tmpPw = Main.userCenter.initUser(email);
				return Response.ok(tmpPw).build();
			} else {
				//invalid email/id combination
				LOGGER.log(Level.WARNING, "Could not activate user, as '" + email + "'/'" + hash
						+ "' (email/id) is not valid!");
				return Response.status(Status.FORBIDDEN).build();
			}
		} catch (Throwable t) {
			String warning = "Could not register user, not enough data provided OR wrong JSON format: '" + content
					+ "'";
			LOGGER.log(Level.WARNING, warning, t);
			return Response.status(Status.INTERNAL_SERVER_ERROR).entity(t.getLocalizedMessage()).build();
		}
	}

	@GET
	@Path("/test")
	@Produces("text/plain")
	public Response test() {
		return Response.ok("Hello world!").build();
	}
}
