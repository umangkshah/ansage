package webSvcs;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import java.util.ArrayList;
import java.util.List;

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
    public Response addQuestion(String jon) {
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		
		try {
			json = (JSONObject) parser.parse(jon);
			
		} catch (ParseException e) {
			   return Response.status(202).entity("false").build();

		}
		String g = json.get("apikey").toString();
		if(g.equals("2tansage68y")){
		QuestionClass qc = new QuestionClass();
		String check = qc.quesdetails(json);
	
	   if(check==null)
		{
		   return Response.status(202).entity("false").build();
		
		}
		else
		{
			
			return Response.status(200).entity(check).build();
		}
		}
		else
			return Response.status(204).entity("Auth false").build();
	}
	
	@Path("/search")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
	public Response searchQuestion(String jon)
	{	
		
		
		JSONParser parser = new JSONParser();
		JSONObject json=new JSONObject();
		try
		{
			json = (JSONObject) parser.parse(jon);

		}
		catch (ParseException e) {
			   return Response.status(202).entity("false").build();

		}
		String input=json.get("query").toString();
		String api=json.get("apikey").toString();
		if(api.equals("2tansage68y")){
		String searchstring[]=input.split(" ");
		ArrayList<String> quslist=new ArrayList<String>();
		for(int i=0;i<searchstring.length;i++)
		{	
			if(searchstring[i].length() > 2){
				quslist.add(searchstring[i]);
			}
			
		}
		QuestionClass qs=new QuestionClass();
		JSONArray jsonlist=qs.searchlist(quslist);
		if(jsonlist==null)
		{
			return Response.status(202).entity("false").build();

		}
		else
		{
			return Response.ok().entity(jsonlist.toString()).build();

			
		}
		}else
			return Response.status(204).entity("Atuh false").build();
	}
	
	
	
	
	@Path("/getq")
	@POST
	@Consumes(MediaType.TEXT_PLAIN)
    public Response getQuestion(String qid) {
		String ip[] = qid.split(" ");
		if(ip[1].equals("2tansage68y")){
		QuestionClass qc = new QuestionClass();
		JSONObject jo = qc.retrievequs(Integer.parseInt(ip[0])); 
		return Response.status(200).entity(jo.toString()).build();
		}
		else
			return Response.status(204).entity("Auth Failure").build();
	}
	
	@Path("/displayq/2tansage68y")
	@GET
	public Response displayQuestion()
	{
		QuestionClass qc = new QuestionClass();
		
		List<Questionpojo> quslist=qc.displayqus();
		JSONArray ja=new JSONArray();
		if(quslist==null)
		{
			return Response.status(202).entity("false").build();
		}
		else
		{
			for(Questionpojo u:quslist)
			{
				
				JSONObject json=new JSONObject();
				json.put("qid",u.getQid());
				json.put("mainQ",u.getQus());
				json.put("descrQ",u.getDescr());
				json.put("ownerid",u.getOwnerid());
				ja.add(json);
			}
			return Response.ok().entity(ja.toString()).build();
			
			
		}
		
	}
}

