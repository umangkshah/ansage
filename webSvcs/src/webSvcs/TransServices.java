package webSvcs;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.Iterator;
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
		String ip[] = bidid.split(" ");
		if(ip[1].equals("2tansage68y")){
		TransClass trans=new TransClass();
		String check=trans.inscart(ip[0]);
		if(check==null)
		{
			 return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(200).entity("true").build();
		}
		}
		else
		 return Response.status(204).entity("Auth Failed").build();
	}
	@Path("/remove")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response removecart(String bidid)
	{	String ip[] = bidid.split(" ");
		if(ip[1].equals("2tansage68y")){
		TransClass trans=new TransClass();
		String check=trans.removecart(ip[0]);
		if(check==null)
		{
			 return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(200).entity("true").build();
		}
		}else
			return Response.status(204).entity("Auth Fail").build();
	}
	@Path("/retrieve")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response retcart(String oid)
	{	
		String ip[] = oid.split(" ");
		if(ip[1].equals("2tansage68y")){
		
		TransClass trans=new TransClass();
		List<JSONObject>translist=trans.retcart(ip[0]);
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
		}else
			return Response.status(204).entity("Auth false").build();
	}
	@Path("/checkout")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response checkout(String oid)
	{	String ip[] = oid.split(" ");
		if(ip[1].equals("2tansage68y")){
		TransClass trans=new TransClass();
		ArrayList emaillist=trans.checkout(ip[0]);
		String maillist=new String();
		int i;
		if(emaillist==null)
		{
			return Response.status(202).entity("false").build();
		}
		else
		{
			
			for(i=0;i<emaillist.size()-1;i++)
			{
				
				String k=(String)emaillist.get(i);
				maillist=maillist+k+" ";
			}
			maillist=maillist+(String)emaillist.get(i);
			return Response.status(200).entity(maillist).build();
		}
		}else
			return Response.status(204).entity("Auth Fail").build();
	}
	@Path("/updateqty/2tansage68y")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response updateqty(String jsonobj)
	{
		TransClass trans=new TransClass();
		Object object=null;
		JSONParser jsonParser=new JSONParser();
		try {
			object=jsonParser.parse(jsonobj);
			} 
		catch (ParseException e)
		{
			
			e.printStackTrace();
		}
		JSONArray arrayobj=new JSONArray();
		arrayobj=(JSONArray)object;
		
		String check=trans.updateqty(arrayobj);
		if(check==null)
		{
			 return Response.status(202).entity("false").build();
		}
		else
		{
			return Response.status(200).entity("true").build();
		}
		
	}
	

}




