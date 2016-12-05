

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
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
 * Servlet implementation class Registration
 */
@WebServlet("/Registration")
public class Registration extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() {
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
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Registration.dCV();
		ServletContext context = getServletContext();
		String apikey = context.getInitParameter("apipass");
		String a[] = request.getParameterValues("skills");
		String skills="";
		for(int k=0;k<a.length;k++){
			skills = skills + a[k];
		}
		JSONObject regform = new JSONObject();
		regform.put("emailid",request.getParameter("emailid"));
		regform.put("password",request.getParameter("pass"));
		regform.put("name",request.getParameter("namepls"));
		regform.put("tagline",request.getParameter("tagline"));
		regform.put("bio",request.getParameter("bio"));
		regform.put("skills",skills);
		regform.put("apikey", apikey);
		String proto = "https://";
		ClientConfig cfg = new DefaultClientConfig();
		cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9443/webSvcs");
		
		ClientResponse c = wsvc.path("regservices").path("add").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, regform.toString());
		
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

			/*HttpSession s = request.getSession();
			s.setAttribute("USER",json.get("emailid").toString());
			s.setAttribute("PROID",json.get("profileid").toString());
			s.setAttribute("NAME",json.get("name").toString());
			s.setAttribute("COINS",json.get("coins").toString());
			*/
			response.sendRedirect("registered.jsp");
		}
	}

}
