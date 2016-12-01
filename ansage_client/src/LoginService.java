import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
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
	
	
	protected String findLoc(String ll){
		String tempsvc = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ll+"&key=AIzaSyCtnpJWEJi6c5tqmE6xiay6o-YRTFVPbwk";
		try{
			URL url = new URL(tempsvc);
			HttpURLConnection connection = (HttpURLConnection) url.openConnection();
			connection.setRequestMethod("GET");
			connection.setRequestProperty("Host", "googleapis.com");
			connection.setRequestProperty("Content-Type", 
			        "application/json");
			connection.setDoOutput(true);
			
			BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
			String op="";
			StringBuilder resp = new StringBuilder(); // or StringBuffer if Java version 5+
		    String line;
		    while ((line = rd.readLine()) != null) {
		      resp.append(line);
		      resp.append('\r');
		    }
		    rd.close();
		    op = op + resp.toString();
		    
		    if (connection != null) 
		    	  connection.disconnect();
		
		JSONParser parser = new JSONParser();
		JSONObject json = new JSONObject();
		try {
			json = (JSONObject) parser.parse(op);
			
		} catch (ParseException e) {}
		
		String printop =json.toString();
		
		int k = printop.indexOf("formatted_address");
		k = k + 19;
		int x = printop.indexOf("\"",k);
		int y = printop.indexOf("\"",k+1);
		String z = printop.substring(x+1,y);
		
		return z;
		}
		catch(Exception e){
			return "Unkown";
		}
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
		
		String ll = request.getParameter("locn");
		String locn="";
		//LoginService ls = new LoginService();
		//locn = ls.findLoc(ll);
		//loginform.put("address", locn);
		
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
