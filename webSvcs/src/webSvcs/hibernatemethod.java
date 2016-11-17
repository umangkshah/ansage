package webSvcs;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;
import org.json.simple.JSONObject;




public class hibernatemethod {

    private static SessionFactory sessionfactory;
    
   
	public static String registration(JSONObject regdata)
	{
		
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		
		
		
	   
	    String username=regdata.get("username").toString();
		String email=regdata.get("email").toString();
		String tagline=regdata.get("tagline").toString();
		String bio=regdata.get("bio").toString();
		String password=regdata.get("password").toString();
		String con=regdata.get("coins").toString();
		String skills=regdata.get("skills").toString();
		int coins=Integer.parseInt(con);
		hibernatepojo reg=new hibernatepojo();
		
		login logdet=new login();
		logdet.setEmail(email);
		logdet.setPassword(password);
		
		reg.setName(username);
		reg.setEmail(email);
		reg.setTagline(tagline);
		reg.setBioinfo(bio);
	     reg.setCoins(coins);
	     
		
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
		tx = session.beginTransaction();
		session.save(reg);
		session.save(logdet);
		tx.commit();
		}
		catch(Exception e)
		{
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
				return "Failed";
		}
		finally
		{
			session.close();
		}
	
		
		
		
		sessionfactory.close();
		return "success";
		
}
	
	@SuppressWarnings("deprecation")
	public  hibernatepojo  login(JSONObject logindet)
	{
		 hibernatepojo reg=null;	
		 login prof=null;
		String email=logindet.get("email").toString();
		String password=logindet.get("password").toString();
		
		
		
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		
		
		
		
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
		     @SuppressWarnings("rawtypes")
		     Query query=session.createQuery("from login where email=:email and password=:password");
		    query.setParameter("email",email);
		    query.setParameter("password",password);
		    
		     prof=(login)query.uniqueResult();
		     
		     tx.commit();
		}
		catch(HibernateException e)
		{
			
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			
			session.close();
			
		}
		int profileid=prof.getProfileid();
		try
		{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		@SuppressWarnings("rawtypes")
		Query query=session.createQuery("from hibernatepojo where profileid=:profileid");
		query.setParameter("profileid",profileid);
		reg=(hibernatepojo)query.uniqueResult();
		tx.commit();
		
		}
		catch(HibernateException e)
		{
			
			if(tx!=null)
				tx.rollback();
			e.printStackTrace();
		}
		finally
		{
			
			session.close();
			
		}
		
		
		sessionfactory.close();
		return reg;
	}
	
}