

import java.io.IOException;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.ws.rs.core.MediaType;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

/**
 * Servlet implementation class Search
 */
@WebServlet("/Search")
public class Search extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Search() {
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
		Search.dCV();
		String str = request.getParameter("query");
		String apikey = "2tansage68y";
		JSONObject json = new JSONObject();
		json.put("query", str);
		json.put("apikey", apikey);
		
		String proto = "https://";
		
		ClientConfig cfg = new DefaultClientConfig();
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9443/webSvcs");
		ClientResponse c = wsvc.path("qservices").path("search").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, json.toString());
		List<QuestionPojo> bidlist=new ArrayList<QuestionPojo>();
		List<String> nams = new ArrayList<String>();
		if (c.getStatus() != 200) {
			response.getOutputStream().print(c.getStatus());
		}
		else{
			
			String jsonstring = c.getEntity(String.class);
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
						
			while(i.hasNext())
			{
				QuestionPojo qu=new QuestionPojo();
				JSONObject jon=(JSONObject)i.next();
				
				String con=jon.get("coins").toString();
				int coins=Integer.parseInt(con);
				qu.setOcoins(coins);
				
				String name=jon.get("oname").toString();
				qu.setOname(name);
				if(!nams.contains(name)){
					nams.add(name);
				}
				
				String reqd=jon.get("bidcount").toString();
				int bdc=Integer.parseInt(reqd);
				qu.setNbids(bdc);
				
				String od=jon.get("oid").toString();
				int oid=Integer.parseInt(od);
				qu.setOid(oid);
				
				String qs=jon.get("mainQ").toString();
				qu.setQuestion(qs);

				String qud=jon.get("qid").toString();
				int quid=Integer.parseInt(qud);
				qu.setQid(quid);
				
				String ds=jon.get("descrQ").toString();
				qu.setDescr(ds);
								
				String skills=jon.get("category").toString();
				qu.setCategory(skills);

				bidlist.add(qu);
			}
			
			request.setAttribute("rows",bidlist);
			request.setAttribute("names", nams);
			request.setAttribute("results", "1");
			RequestDispatcher dispatcher = request.getRequestDispatcher("search.jsp");
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
		doGet(request, response);
	}

}
