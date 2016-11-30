package webSvcs;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("/transservices")
public class TransServices {
	@Path("/add")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response addCart(String bidid)
	{
		TransClass trans=new TransClass();
		String check=trans.inscart(bidid);
		if(check==null)
		{
			 return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(200).entity("true").build();
		}
		
	}
	@Path("/remove")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removecart(String bidid)
	{
		TransClass trans=new TransClass();
		String check=trans.removecart(bidid);
		if(check==null)
		{
			 return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(200).entity("true").build();
		}
		
	}
	@Path("/retrieve")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response retcart(String oid)
	{
		TransClass trans=new TransClass();
		List<JSONObject>translist=trans.retcart(oid);
		JSONArray ja=new JSONArray();
		if(translist==null)
		{
			return Response.status(202).entity("false").build();
			
		}
		else
		{
			
			for(JSONObject td:translist)
			{
				ja.add(td);
			}
			return  Response.ok().entity(ja.toString()).build();
			
		}
	}
	@Path("/checkout")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response checkout(String oid)
	{
		TransClass trans=new TransClass();
		String check=trans.checkout(oid);
		if(check==null)
		{
			return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(202).entity("true").build();
			
		}
		
		
		
		
		
		
	}
	
	

}




