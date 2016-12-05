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

@Path("/updatecoin")
public class UpdateServices {
	@GET
	@Consumes(MediaType.TEXT_PLAIN)
	@Path("/coins/{oid}/{key}")
	public Response updatecoins(@PathParam("oid") String oid,@PathParam("key") String key)
	{	if(key.equals("2tansage68y")){
		ProfileClass prof=new ProfileClass();
		String coins=prof.getcoins(oid);
		if(coins==null)
		{
		 return Response.status(202).entity("false").build();
			   
		}
		else
		{
			return Response.status(200).entity(coins).build();

		}
	}else
		return Response.status(204).entity("Auth false").build();
	}
}

