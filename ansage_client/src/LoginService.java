

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.sun.jersey.api.client.Client;
import com.sun.jersey.api.client.ClientResponse;
import com.sun.jersey.api.client.WebResource;

/**
 * Servlet implementation class LoginService
 */
@WebServlet("/LoginService")
public class LoginService extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginService() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		//doGet(request, response);
		Client cl = Client.create();
		String username = request.getParameter("emailid");
		try{
		 WebResource ws = cl.resource("http://localhost:9080/webSvcs/loginservices/availableusername/" + username);
		 ClientResponse e = ws.accept("").get(ClientResponse.class);
		 if (e.getStatus() != 200) {
		 throw new RuntimeException("Failed : HTTP error code : " + e.getStatus());
		 }
		//String pass = request.getParameter("passwd");
		/*
		 * LoginBean lb= new LoginBean();
		 * lb.uname = username;
		 * lb.password = pass;
		 * web svc call (localhost:8080/ansage/doLogin/) 
		 */
		 username = e.getEntity(String.class);
		HttpSession s = request.getSession();
		s.setAttribute("USER",username);
		response.sendRedirect("index.jsp");
		}
		catch(Exception e){}
	}

}
