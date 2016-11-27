

import java.io.IOException;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@WebServlet("/DisplayQuestion")

public class DisplayQuestion extends HttpServlet {
	
	public DisplayQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }
	
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		Response responsebuild;
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		//cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		
		ClientResponse c = wsvc.path("qservices").path("displayq").type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		
		if (c.getStatus() != 200) {
			response.getOutputStream().print(c.getStatus());
 
			}
		else
		{  String jsonstring = c.getEntity(String.class);
			Object object=null;
			JSONParser jsonParser=new JSONParser();
			try {
				object=jsonParser.parse(jsonstring);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			JSONArray arrayObj=null;
			arrayObj=(JSONArray)object;
			Iterator i = arrayObj.iterator();
			
				System.out.println(arrayObj.get(0));
			/*while(i.hasNext())
			{
				JSONObject jon=(JSONObject)i.next();
				String qid=jon.get("qid").toString();
				System.out.println(qid);
			}*/
			
			
			
		}
		
		
	}

	
	
}
