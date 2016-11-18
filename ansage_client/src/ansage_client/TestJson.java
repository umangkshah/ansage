package ansage_client;

import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class TestJson {
	public static void main(String args[]){
		String x;
		JSONObject j = new JSONObject();
		j.put("username", "uks@gmail.com");
		j.put("password","12345");
		System.out.println(j.toString());
		x = j.toString();
		JSONParser parser = new JSONParser();
		JSONObject json;
		try{
			json = (JSONObject) parser.parse(x);
			System.out.println(json.get("username"));
		}catch(Exception e){}
	}
	
	
}
