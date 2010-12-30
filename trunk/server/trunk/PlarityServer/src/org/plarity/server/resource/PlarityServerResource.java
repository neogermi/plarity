package org.plarity.server.resource;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.plarity.server.Main;

@Path("/")
public class PlarityServerResource {

	@POST
	@Consumes("application/json")
	@Produces("text/plain")
	@Path("/register")
	public Response registerUser(String content) {
		try {
			boolean res = Main.userCenter.registerUser("neogermi@test.de", "neogermi");

			if (res) {
				return Response.ok().build();
			} else {
				return Response.status(Status.CONFLICT).build();
			}
		} catch (Throwable t) {
			t.printStackTrace();
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
