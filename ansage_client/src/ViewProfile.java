

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletContext;
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
    public static void dCV() {
	    // Create a trust manager that does not validate certificate chains
	    TrustManager[] trustAllCerts = new TrustManager[] { 
	      new X509TrustManager() {
	        public X509Certificate[] getAcceptedIssuers() { 
	          return new X509Certificate[0]; 
	        }
	        public void checkClientTrusted(X509Certificate[] certs, String authType) {}
	        public void checkServerTrusted(X509Certificate[] certs, String authType) {}
	    }};

	    try {
	      SSLContext sc = SSLContext.getInstance("SSL");
	      sc.init(null, trustAllCerts, new SecureRandom());
	      HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
	      //HttpsURLConnection.setDefaultHostnameVerifier(hv);
	    } catch (Exception e) {}
	  }
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		ViewProfile.dCV();
		ServletContext context = getServletContext();
		String apikey = context.getInitParameter("apipass");
		String profid = request.getParameter("profile");
		int pid = Integer.parseInt(profid);
		//get profile from websvc by pid into json
		String proto = "https://";
		ClientConfig cfg = new DefaultClientConfig();
		Client cl = Client.create(cfg);
		// a/id/key
		WebResource wsvc = cl.resource(proto+"localhost:9443/webSvcs/profservices/getprofile/" + profid + "/"+apikey);
		
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
			p.setCoins(json.get("coins").toString());
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
