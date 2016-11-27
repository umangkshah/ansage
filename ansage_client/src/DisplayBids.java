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
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.GenericType;
import com.sun.jersey.api.client.WebResource;
import com.sun.jersey.api.client.config.ClientConfig;
import com.sun.jersey.api.client.config.DefaultClientConfig;

@WebServlet("/DisplayBids")


public class DisplayBids extends HttpServlet {
	public DisplayBids() {
        super();
        }
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
	
		String qd=request.getParameter("qid").toString();
		List<BidPojo> bidlist=new ArrayList<BidPojo>();
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		//cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		ClientResponse c = wsvc.path("bidservices").path("retrieve").path(qd).type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		if (c.getStatus() != 200) {
			response.getOutputStream().print("Error");
			}
		else
		{  String jsonstring = c.getEntity(String.class);
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
				BidPojo qu=new BidPojo();
				JSONObject jon=(JSONObject)i.next();
				String qud=jon.get("qid").toString();
				int qid=Integer.parseInt(qud);
				String reqd=jon.get("reqid").toString();
				int reqid=Integer.parseInt(reqd);
				String offr=jon.get("offer").toString();
				int offer=Integer.parseInt(offr);
				String bd=jon.get("bidid").toString();
				int bidid=Integer.parseInt(bd);
				qu.setBidid(bidid);
				qu.setOffer(offer);
				qu.setQid(qid);
				qu.setReqid(reqid);
				bidlist.add(qu);
				
			}
			
			request.setAttribute("rows",bidlist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("displayBids.jsp");
			if(dispatcher != null){
				dispatcher.forward(request,response);
			}
			
		}
	
	}
}






	
	

