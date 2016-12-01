

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
 * Servlet implementation class ViewCart
 */
@WebServlet("/ViewCart")
public class ViewCart extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewCart() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oid = request.getSession().getAttribute("PROID").toString();
		
		List<CartPojo> cartlist=new ArrayList<CartPojo>();
		//Call webSvc
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		
		ClientResponse c = wsvc.path("transservices").path("retrieve").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, oid);
		
		
			if(c.getStatus() != 200){
				response.getOutputStream().print(c.getStatus());
			}
			else
			{
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
					CartPojo ck=new CartPojo();
					JSONObject jon=(JSONObject)i.next();
					
					String reqd=jon.get("reqid").toString();
					int reqid=Integer.parseInt(reqd);
					String offr=jon.get("offer").toString();
					int offer=Integer.parseInt(offr);
					String bd=jon.get("bidid").toString();
					int bidid=Integer.parseInt(bd);
					String name=jon.get("rname").toString();
					String qtty=jon.get("qty").toString();
					int qty=Integer.parseInt(qtty);
					
					ck.setBidid(bidid);
					ck.setOffer(offer);
					ck.setReqid(reqid);
					ck.setName(name);
					ck.setQty(qty);
					
					cartlist.add(ck);
				}
				
				request.setAttribute("rows",cartlist);
				RequestDispatcher dispatcher = request.getRequestDispatcher("cart.jsp");
				if(dispatcher != null){
					dispatcher.forward(request,response);
				}
			}
	}
	
}
