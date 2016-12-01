package ansage_client;

import java.io.IOException;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;

/**
 * Servlet implementation class SimpleTest
 */
@WebServlet("/SimpleTest")
public class SimpleTest extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SimpleTest() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		
		//String ll = request.getParameter("locn");
		String ll = "43.005487699999996,-90.7119513";
		
		String tempsvc = "https://maps.googleapis.com/maps/api/geocode/json?latlng="+ll+"&key=AIzaSyCtnpJWEJi6c5tqmE6xiay6o-YRTFVPbwk";
		
		URL url = new URL(tempsvc);
		HttpURLConnection connection = (HttpURLConnection) url.openConnection();
		connection.setRequestMethod("GET");
		connection.setRequestProperty("Host", "googleapis.com");
		connection.setRequestProperty("Content-Type", 
		        "application/json");
		connection.setDoOutput(true);
		
		BufferedReader rd = new BufferedReader(new InputStreamReader(connection.getInputStream()));
		String op="";
		StringBuilder resp = new StringBuilder(); // or StringBuffer if Java version 5+
	    String line;
	    while ((line = rd.readLine()) != null) {
	      resp.append(line);
	      resp.append('\r');
	    }
	    rd.close();
	    op = op + resp.toString();
	    
	    if (connection != null) 
	    	  connection.disconnect();
	    
	    
		
		JSONParser parser = new JSONParser();
			JSONObject json = new JSONObject();
			
			
			try {
				json = (JSONObject) parser.parse(op);
				
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
		
		String printop =json.toString();
		
		int k = printop.indexOf("formatted_address");
		k = k + 19;
		int x = printop.indexOf("\"",k);
		int y = printop.indexOf("\"",k+1);
		String z = printop.substring(x+1,y);
		
		response.getWriter().append(z);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
