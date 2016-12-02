

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
 * Servlet implementation class MyQuestions
 */
@WebServlet("/MyQuestions")
public class MyQuestions extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public MyQuestions() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		List<QuestionPojo> qulist=new ArrayList<QuestionPojo>();
		
		String proto = "http://";
		ClientConfig cfg = new DefaultClientConfig();
		//cfg.getClasses().add(JacksonJsonProvider.class);
		Client cl = Client.create(cfg);
		WebResource wsvc = cl.resource(proto+"localhost:9080/webSvcs");
		
		ClientResponse c = wsvc.path("qservices").path("displayq").type(MediaType.TEXT_PLAIN).accept(MediaType.TEXT_PLAIN).get(ClientResponse.class);
		
		if (c.getStatus() != 200) {
			response.getOutputStream().print(c.getStatus());
 
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
			int prid = Integer.parseInt(request.getSession().getAttribute("PROID").toString());
			Iterator i = arrayObj.iterator();
			
			
			while(i.hasNext())
			{
				QuestionPojo qu=new QuestionPojo();
				JSONObject jon=(JSONObject)i.next();
				String qd=jon.get("qid").toString();
				int qid=Integer.parseInt(qd);
				String ques=jon.get("mainQ").toString();
				String descr=jon.get("descrQ").toString();
				String oid=jon.get("ownerid").toString();
				int ownerid=Integer.parseInt(oid);
				if(prid == ownerid){
					qu.setQid(qid);
					qu.setOid(ownerid);
					qu.setQuestion(ques);
					qu.setDescr(descr);
					qulist.add(qu);
				}
			}
			
			request.setAttribute("rows",qulist);
			RequestDispatcher dispatcher = request.getRequestDispatcher("displayQuestion.jsp");
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
