import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;
import org.json.simple.JSONObject;
//import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;


@WebServlet("/EditProfile")
public class EditProfile extends HttpServlet
{
	
	public EditProfile () {
		super();
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String a[] = request.getParameterValues("skills");
		String skills="";
		for(int k=0;k<a.length;k++){
			skills = skills + a[k];
			}
		String pid = request.getParameter("profid");
		JSONObject updateform=new JSONObject();
		updateform.put("name",request.getParameter("name"));
		updateform.put("tagline",request.getParameter("tagline"));
		updateform.put("bio",request.getParameter("bio"));
		updateform.put("skills",skills);
		updateform.put("pid", pid);
		
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		//cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		ClientResponse c = wsvc.path("profservices").path("edit").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, updateform.toString());
		ProfilePojo p=new ProfilePojo();
		if (c.getStatus() != 200) {
			response.getOutputStream().print("Error");
			}
			else{
				String name=updateform.get("name").toString();
				String tagline=updateform.get("tagline").toString();
				String bio=updateform.get("bio").toString();
				String skill=updateform.get("skills").toString();
				 p=new ProfilePojo();
				p.setBio(bio);
				p.setName(name);
				p.setTagline(tagline);
				p.setSkills(skill);
			}
		request.setAttribute("rows",p);
		String redlink = "ViewProfile?profile="+pid;
		RequestDispatcher dispatcher = request.getRequestDispatcher(redlink);
		if(dispatcher != null){
		dispatcher.forward(request,response);
		}
		
	}	
}