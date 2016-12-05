

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

import com.fasterxml.jackson.jaxrs.json.JacksonJsonProvider;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Question
 */
@WebServlet("/Question")
public class Question extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Question() {
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
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		Question.dCV();
		ServletContext context = getServletContext();
		String apikey = context.getInitParameter("apipass");
		String a[] = request.getParameterValues("skills");
		String skills="";
		for(int k=0;k<a.length;k++){
			skills = skills + a[k];
		}
		JSONObject qsn = new JSONObject();
		HttpSession s = request.getSession();
		qsn.put("ownerid",s.getAttribute("PROID"));
		qsn.put("mainQ",request.getParameter("mainQ"));
		qsn.put("descrQ",request.getParameter("descrQ"));
		qsn.put("category", skills);
		qsn.put("apikey", apikey);
		String proto = "https://";
		ClientConfig cfg = new DefaultClientConfig();
		cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9443/webSvcs");
		
		ClientResponse c = wsvc.path("qservices").path("add").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, qsn.toString());
		
		if (c.getStatus() != 200) {
		response.getOutputStream().print("Error");
		}
		else{
			
			response.sendRedirect("asked.jsp");
		}
	}

}
