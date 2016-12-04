package webSvcs;                 

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.json.simple.JSONObject;


public class ProfileClass 
	{
	private static SessionFactory sessionfactory;
	
	
	public  Registrationpojo profile(String id)
		{
		Registrationpojo prof=null;
		Transaction tx=null;
		Session session=null;
		int profileid=Integer.parseInt(id);
		try
			{
			sessionfactory=new Configuration().configure().buildSessionFactory();
			}
		catch(Exception ex)
			{
			return null;
			}
		
		try
			{
			 session=sessionfactory.openSession();
			 tx=null;
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
			query.setParameter("profileid",profileid);
			prof=(Registrationpojo)query.uniqueResult();
			
			tx.commit();
			if(prof==null)
			return null;
			}
	 	catch(HibernateException e)
			{
			if(tx!=null)
			tx.rollback();
			return null;
			}
		finally
			{
			
			session.close();
			}
		sessionfactory.close();
		return prof;
		}
	
	public String editprofile(JSONObject regdata)
	{
		Session session=null;
		Transaction tx=null;
		String name=regdata.get("name").toString();
		//String email=regdata.get("emailid").toString();
		String tagline=regdata.get("tagline").toString();
		String bioinfo=regdata.get("bio").toString();
		//String password=regdata.get("password").toString();
		String skills=regdata.get("skills").toString();
		String profid=regdata.get("pid").toString();
		int profileid=Integer.parseInt(profid);
		//String coin=regdata.get("coins").toString();
		//int coins=Integer.parseInt(coin);
		try
		{
		sessionfactory=new Configuration().configure().buildSessionFactory();
		}
	catch(Exception ex)
		{
		return null;
		}
		try
		{
			session=sessionfactory.openSession();
		    tx=session.beginTransaction();
		    Query query=session.createQuery("UPDATE Registrationpojo set name=:name,tagline=:tagline,bioinfo=:bioinfo,skills=:skills where profileid=:profileid");
		    query.setParameter("name",name);
		   // query.setParameter("email",email);
		    //query.setParameter("coins",coins);
		    query.setParameter("tagline",tagline);
		    query.setParameter("bioinfo",bioinfo);
		    query.setParameter("skills",skills);
		    query.setParameter("profileid",profileid);
		    int result=query.executeUpdate();
		    if(result==0)
		    	return "false";
		}
		catch(Exception e)
		{
			if(tx!=null)
			tx.rollback();
			
			System.out.println(e.getMessage());
			return null;
		}
		finally
		{
			session.close();
			
		}
		sessionfactory.close();
		return "true";
	}
	
}
	
	

