

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class ViewProfile
 */
@WebServlet("/ViewProfile")
public class ViewProfile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewProfile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String profid = request.getParameter("profile");
		int pid = Integer.parseInt(profid);
		//get profile from websvc by pid into json
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs/profservices/getprofile/" + profid);
		
		ClientResponse c = wsvc.type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				get(ClientResponse.class);
		
		if (c.getStatus() != 200) {
		response.getOutputStream().print(c.getStatus());
		}
		else{
			
			String jon = c.getEntity(String.class);
			JSONParser parser = new JSONParser();
			JSONObject json = new JSONObject();
			try {
				json = (JSONObject) parser.parse(jon);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			ProfilePojo p = new ProfilePojo();
			p.setName(json.get("name").toString());
			p.setBio(json.get("bio").toString());
			p.setTagline(json.get("tagline").toString());
			p.setSkills(json.get("skills").toString());
			p.setProid(pid);
			
			request.setAttribute("rows", p);
			RequestDispatcher dispatcher = request.getRequestDispatcher("profile.jsp");
			if(dispatcher != null){
				dispatcher.forward(request,response);
			}
		}
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
	}

}
