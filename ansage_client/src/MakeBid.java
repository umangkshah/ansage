

import java.io.IOException;
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
 * Servlet implementation class MakeBid
 */
@WebServlet("/MakeBid")
public class MakeBid extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MakeBid() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		JSONObject bidform = new JSONObject();
		HttpSession s = request.getSession();
		Object qid = request.getParameter("qid");
		bidform.put("qid", request.getParameter("qid"));
		bidform.put("offer", request.getParameter("offerval"));
		bidform.put("reqid",s.getAttribute("PROID"));
		
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		
		ClientResponse c = wsvc.path("bidservices").path("add").
				type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).
				post(ClientResponse.class, bidform.toString());
		
		if (c.getStatus() != 200) {
			response.getOutputStream().print("Error: " +c.getStatus() + " Some Problem while making Bid");
			}
			else{
				String gotopage = "ViewQuestion?question="+ qid.toString();
				response.getOutputStream().print("Checking error");
				response.sendRedirect(gotopage);
			}
		
	}

}
