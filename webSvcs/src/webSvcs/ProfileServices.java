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
	
	@Path("/getprofile/{id}")
	@Consumes(MediaType.TEXT_PLAIN)
	public Response getprofile(@PathParam("id")String id)
	{
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
		JSONObject json=new JSONObject();
		json.put("name",name);
		json.put("tagline",tagline);
		json.put("bio",bio);
		json.put("skills",skills);
		json.put("proid",profileid);
		return Response.status(200).entity(json.toString()).build();
	}
	@Path("/edit")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response updateprofile(String jon)
	{
		
		
	}

}





