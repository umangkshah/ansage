

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class ViewQuestion
 */
@WebServlet("/ViewQuestion")
public class ViewQuestion extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ViewQuestion() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		int qid = Integer.parseInt(request.getParameter("question"));
		QuestionPojo qp = new QuestionPojo();
		//qp.setQuestion(json.get("question"));
		qp.setQuestion("can penguins fly?");
		qp.setOname("Rahul");
		qp.setDescr("A very intriguing question");
		qp.setQid(23);
		qp.setOid(12);
		request.setAttribute("qdets", qp);
		RequestDispatcher dispatcher = request.getRequestDispatcher("viewquestion.jsp");
		if(dispatcher != null){
			dispatcher.forward(request,response);
		}
	}

}
