

import java.io.IOException;

import javax.mail.Address;
import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class TestEmail
 */
@WebServlet("/TestEmail")
public class TestEmail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public TestEmail() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		EmailUtility eu = new EmailUtility();
		String a[] = {"sreeni.venki@gmail.com","uks160030@utdallas.edu","umang.k.shah@gmail.com","sreenivenki@gmail.com"};
		InternetAddress[] bcc = new InternetAddress[a.length];
		for(int i=0;i<a.length;i++){
			try {
				
				bcc[i] = new InternetAddress(a[i]);
				response.getWriter().append(a[i]+"  ").append(bcc[i].toString()+"\n");
			} catch (AddressException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		String o = "umangkshah@gmail.com";
		String sub ="Test";
		String c1 = "hi owner";
		String c2 = "hi bidder";
		String host="smtp.gmail.com";
		String user="ansage.tsvc@gmail.com";
		String pass="ansage123";
		try{
			eu.sendEmail(host,"587",user,pass,bcc,o,sub,c2,c1);	
		}catch(Exception e){
			e.printStackTrace();
		}
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
