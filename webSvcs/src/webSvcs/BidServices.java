package webSvcs;


import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import java.util.*;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.GenericEntity;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("/bidservices")
public class BidServices {
	@Path("/add")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response saveBid(String jon)
		{
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try 
			{
			json = (JSONObject) parser.parse(jon);
			//System.out.println("here printing " + json);
			} 
		catch (ParseException e) 
			{
			// TODO Auto-generated catch block
			e.printStackTrace();
			}
		String gh = json.get("apikey").toString();
		if(gh.equals("2tansage68y")){
		BidClass bid = new BidClass();
		String check = bid.savebid(json); 
		System.out.println(check);
		
		if(check=="false")
			{
			   return Response.status(202).entity("false").build();
			}
			else
			{
				//return Response.status(202).entity("false").build();
			return Response.status(200).entity(check).build();
			}
		}else
			return Response.status(202).entity("Auth false").build();
		}
	
	@Path("/retrieve/{qid}/{key}")
	@GET
    public Response retbid(@PathParam("qid") String qd,@PathParam("key") String key)
    	{
		  if(key.equals("2tansage68y")){
		  BidClass bid=new BidClass();
		  List<JSONObject> bidlist=bid.retbids(qd);
		  JSONArray ja=new JSONArray();
		  if(bidlist==null)
			  return Response.status(202).entity("false").build();
		  else
		  {
			 for(JSONObject b:bidlist)
			 {
				 
				 ja.add(b);
			 }
			  return  Response.ok().entity(ja.toString()).build();
		  }
		}else
			return Response.status(204).entity("Auth false").build();
    	}
    	
			
}
	

