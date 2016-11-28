import java.io.IOException;
import java.util.Map;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.simple.*;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.fasterxml.jackson.*;
import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.*;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.*;;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		
		JSONObject loginform = new JSONObject();
		loginform.put("username", request.getParameter("emailid"));
		loginform.put("password", request.getParameter("passwd"));
		
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		/*
		String ll = request.getParameter("locn");
		String tempsvc = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ll+"&key=AIzaSyCtnpJWEJi6c5tqmE6xiay6o-YRTFVPbwk";
		WebResource locsvc = cl.resource(tempsvc);
		JSONObject locresp = locsvc.get(JSONObject.class);
		String address = locresp.get("formatted_address").toString();
		loginform.put("address", address);
		*/
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		
		ClientResponse c = wsvc.path("loginservices").path("checkuservalidity").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, loginform.toString());
		if (c.getStatus() != 200) {
			response.getOutputStream().print("Error");
		}
		else{
			String respn = c.getEntity(String.class);
			JSONParser parser = new JSONParser();
			JSONObject json = new JSONObject();
			
			
			try {
				json = (JSONObject) parser.parse(respn);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			
			HttpSession s = request.getSession();
			s.setAttribute("USER",json.get("emailid").toString());
			s.setAttribute("PROID",json.get("profileid").toString());
			s.setAttribute("NAME",json.get("name").toString());
			s.setAttribute("COINS",json.get("coins").toString());
			//request.setAttribute("prevdate", json.get("date").toString());
			
			response.sendRedirect("index.jsp");
			
		}
		
	
	}

}
