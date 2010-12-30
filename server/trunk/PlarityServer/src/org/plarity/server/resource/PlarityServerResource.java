package org.plarity.server.resource;

import java.util.logging.Level;
import java.util.logging.Logger;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
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
			String name = data.getString("name");

			if (Main.userCenter.registerUser(email, name)) {
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
	@Produces("text/plain")
	@Path("/validate/{validate_id}")
	public Response validateUser(@PathParam("validate_id") String validateId) {
		//TODO
		return Response.status(Status.NOT_FOUND).build();
	}

	@GET
	@Path("/test")
	@Produces("text/plain")
	public Response test() {
		return Response.ok("Hello world!").build();
	}
}
