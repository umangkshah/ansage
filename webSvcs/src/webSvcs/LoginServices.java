package webSvcs;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
 
@Path("/loginservices")
public class LoginServices {
	@Path("/checkuservalidity")
	@POST
	@Consumes("application/x-www-form-urlencoded")
    @Produces(MediaType.TEXT_PLAIN)
	public Response isValidUser(MultivaluedMap<String, String> formParam) {
		boolean response = false;
		if(formParam.getFirst("password").equals("12345")){
			response = true;
		}
		else{
			response = false;
		}
		return Response.ok().entity(String.valueOf(response)).build();
	}
	
	@Path("/availableusername/{username}")
	@GET
	@Produces(MediaType.TEXT_HTML)
	public String availableUsername(@PathParam("username") String username) {
		
		return "<p>" + username + "001 </p>";
	}
}

