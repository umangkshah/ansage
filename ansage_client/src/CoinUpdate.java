import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

//import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@WebServlet("/CoinUpdate")
public class CoinUpdate extends HttpServlet {
	
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
	String owd=request.getParameter("oid").toString();
	String proto = "http://";
	ClientConfig cfg = new DefaultClientConfig();
//	cfg.getClasses().add(JacksonJsonProvider.class);
	Client cl = Client.create(cfg);
	
	WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
	ClientResponse c = wsvc.path("updatecoin").path("coins").path(owd).type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
	if (c.getStatus() != 200)
	{
	response.getOutputStream().print("Error");
	}
	else
	{
		String coins=c.getEntity(String.class);
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(coins);
	}
	}
}
	
	
	
	
	
