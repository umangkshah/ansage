

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
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class UpdateCart
 */
@WebServlet("/UpdateCart")
public class UpdateCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateCart() {
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
		UpdateCart.dCV();
		ServletContext context = getServletContext();
		String apikey = context.getInitParameter("apipass");
		String k = request.getParameter("items").toString();
		String pairs[] = k.split("!");
		String op="";
		JSONArray ja = new JSONArray();
		
		
		for(int i=0;i < pairs.length;i++){
			String two[] = pairs[i].split("=");
			
			JSONObject jo = new JSONObject();
			jo.put("bidid",two[0]);
			jo.put("qty", two[1]);
			ja.add(jo);
		}
		/*
		 * KEY!
		JSONObject key = new JSONObject();
		key.put("key",apikey);
		ja.add(key);*/
		String resp="0";
		String proto = "https://";
		ClientConfig cfg = new DefaultClientConfig();
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9443/webSvcs");
		
		ClientResponse c = wsvc.path("transservices").path("updateqty").path(apikey).
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, ja.toString());
		if (c.getStatus() == 200) {
			resp = "1";
		}
		
		
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write(resp);
	}

}
