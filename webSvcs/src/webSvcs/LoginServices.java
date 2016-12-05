package webSvcs;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;
 
@Path("/loginservices")
public class LoginServices {
	@Path("/checkuservalidity")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
    public Response isValidUser(String jon) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		
		try {
			json = (JSONObject) parser.parse(jon);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String apikey = json.get("apikey").toString();
		if(apikey.equals("2tansage68y")){
			RegistrationClass hmethod=new RegistrationClass();
			JSONObject check = hmethod.login(json);
		   if(check==null)
			{
			   return Response.status(202).entity("false").build();
			
			}
			else
			{	
				/*
				check.get("date").toString();
						
				String mail=check.get("emailid").toString();
				
				String coin=check.get("coins").toString();
				*/
				System.out.println(check.toString());
				return Response.status(200).entity(check.toString()).build();
				}
	}
	else{
		return Response.status(200).entity("Unauthorized Access").build();
	}
	
	}
	
	@Path("/availableusername/{username}/{key}")
	@GET
	@Produces(MediaType.TEXT_PLAIN)
	public String availableUsername(@PathParam("username") String username, @PathParam("key") String apikey) {
		//hibernatemethod htm = new hibernatemethod();
		if(apikey.equals("2tansage68y")){
		String check = "false";
		//check=htm.checkUser(username);
		if(check.equals("true"))
			return "true";
		else
			return "false";
	}
		else
			return "unauth";
	}
}

