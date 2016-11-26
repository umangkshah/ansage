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
 
@Path("/qservices")
public class QServices {
	@Path("/add")
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
		
		QuestionClass qc = new QuestionClass();
		String check = qc.quesdetails(json);
	
	   if(check==null)
		{
		   return Response.status(202).entity("false").build();
		
		}
		else
		{
			
			//check.getCoins();
			return Response.status(200).entity(check).build();
		}
	}
	
}

