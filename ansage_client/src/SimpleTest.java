public class SimpleTest{
	public static void main(String arg[]){
		EmailUtility eu = new EmailUtility();
		String a[] = {"uks160030@utdallas.edu","umang.k.shah@gmail.com","sreenivenki@gmail.com"};
		String o = "umangkshah@gmail.com";
		String sub ="Test";
		String c1 = "hi owner";
		String c2 = "hi bidder";
		String host="smtp.gmail.com";
		String user="ansage.tscv@gmail.com";
		String pass="ansage123";
		try{
			eu.sendEmail(host,"587",user,pass,a,o,sub,c1,c2);	
		}catch(Exception e){
			e.printStackTrace();
		}
		
	}
}