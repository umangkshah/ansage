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
import javax.ws.rs.core.MultivaluedMap;
import javax.ws.rs.core.Response;
import javax.ws.rs.PathParam;

@Path("/profservices")
public class ProfileServices {
	
	@Path("/getprofile/{id}/{key}")
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getprofile(@PathParam("id")String id,@PathParam("key")String key)
	{	if(key.equals("2tansage68y")){
		ProfileClass prof=new ProfileClass();
		Registrationpojo getprof=prof.profile(id);
		if(getprof==null)
		{
			return Response.status(202).entity("false").build();
			
		}
		String name=getprof.getName();
		String tagline=getprof.getTagline();
		String bio=getprof.getBioinfo();
		String skills=getprof.getSkills();
		int profileid=getprof.getProfileid();
		int coins=getprof.getCoins();
		JSONObject json=new JSONObject();
		json.put("name",name);
		json.put("tagline",tagline);
		json.put("bio",bio);
		json.put("skills",skills);
		json.put("proid",profileid);
		json.put("coins",coins);
		return Response.status(200).entity(json.toString()).build();
	}else
		return Response.status(200).entity("Auth Fail").build();
	}
	
	@Path("/edit")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response editprofile(String jon)
	{
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(jon);
			} 
		catch (ParseException e) 
		{
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		String g = json.get("apikey").toString();
		if(g.equals("2tansage68y")){
		ProfileClass prof=new ProfileClass();
		String check=prof.editprofile(json);
		
		 if(check==null)
			{
			   return Response.status(202).entity("false").build();
			
			}
		 else
		 	{
			 return Response.status(200).entity("true").build();
		 	}
		}else
			return Response.status(204).entity("Auth false").build();
	}

}





