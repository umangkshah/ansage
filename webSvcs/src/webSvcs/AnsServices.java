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
 @Path("/ansservices")
public class AnsServices {
	
	@Path("/permission")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getperm(String jon)
	{
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(jon);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnswerClass ans=new AnswerClass();
		String check=ans.givepermission(json);
		if(check=="false")
		{
			return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(200).entity("true").build();

		}
			
		
	}
	
	@Path("/saveanswer")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response saveans(String jon)
	{
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(jon);
			
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		AnswerClass ans=new AnswerClass();
		String check=ans.givepermission(json);
		if(check=="false")
		{
			return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(200).entity("true").build();

		}
		
	}
	
}


