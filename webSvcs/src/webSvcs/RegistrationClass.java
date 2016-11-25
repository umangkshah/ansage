package webSvcs;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;
import org.json.simple.JSONObject;
import org.json.simple.*;




public class RegistrationClass {

    private static SessionFactory sessionfactory;
    
 /* public static void main(String [] args)
    
    {
    	JSONObject jon=new JSONObject();
    	
    	jon.put("emailid","qq178@gmail.com");
    	jon.put("name", "john");
    	jon.put("tagline", "hjhjh");
    	jon.put("bio", "jhjjh");
    	jon.put("password", "wdqw");
    	jon.put("skills", "assa");
    	RegistrationClass re=new RegistrationClass();
    	Registrationpojo o=re.registration(jon);    	
    	
	  // Date k=new Date();
	   
    	
    }*/
    
    
   
	public  Registrationpojo registration(JSONObject regdata)
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
		String name=regdata.get("name").toString();
		String email=regdata.get("emailid").toString();
		String tagline=regdata.get("tagline").toString();
		String bio=regdata.get("bio").toString();
		String password=regdata.get("password").toString();
		String skills=regdata.get("skills").toString();
		int coins=25;
		
		Registrationpojo reg=new Registrationpojo();
		Loginpojo logdet=new Loginpojo();
		logdet.setEmail(email);
		logdet.setPassword(password);
		Date date=new Date();
		logdet.setLogindate(date);
		reg.setName(name);
		reg.setEmail(email);
		reg.setTagline(tagline);
		reg.setBioinfo(bio);
	    reg.setCoins(coins);
	    reg.setSkills(skills);
	    Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
		tx = session.beginTransaction();
		int id=(Integer)session.save(reg);
		logdet.setProfileid(id);
		session.save(logdet);
		tx.commit();
		}
		catch(Exception e)
		{
			if(tx!=null)
			tx.rollback();
			e.printStackTrace();
			return null;
			
		}
		finally
		{
			session.close();
		}
		sessionfactory.close();
		return reg;
	}
	
	@SuppressWarnings("deprecation")
	public  JSONObject  login(JSONObject logindet)
	{
		 Registrationpojo reg=null;	
		 Loginpojo prof=null;
		String email=logindet.get("username").toString();
		String password=logindet.get("password").toString();
		JSONObject logdet=new JSONObject();
		Date date=null;
		
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
		     Query query=session.createQuery("from Loginpojo where email=:email and password=:password");
		    query.setParameter("email",email);
		    query.setParameter("password",password);
		    
		     prof=(Loginpojo)query.uniqueResult();
		     
		     tx.commit();
		     if(prof==null)
		    	 return null;
		      date=prof.getLogindate();
		     
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
		
		
		try
		{  Date logindate=new Date();
		System.out.println(logindate);
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
		     @SuppressWarnings("rawtypes")
		     Query query=session.createQuery("UPDATE Loginpojo set logindate=:logindate WHERE email=:email and password=:password");
		    query.setParameter("email",email);
		    query.setParameter("password",password);
		    query.setParameter("logindate",logindate);
			query.executeUpdate();
			tx.commit();
		 }
		catch(HibernateException e)
		{
			
			if(tx!=null)
				tx.rollback();
			
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
		Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
		query.setParameter("profileid",profileid);
		reg=(Registrationpojo)query.uniqueResult();
		tx.commit();
		if(reg==null)
			return null;
		int pid=reg.getProfileid();
		String pd=String.valueOf(pid);
		String mail=reg.getEmail();
		String name=reg.getName();
		int coin=reg.getCoins();
		String coins=String.valueOf(coin);
		logdet.put("date",date);
		logdet.put("profileid",pd);
		logdet.put("emailid",mail);
		logdet.put("name",name);
		logdet.put("coins",coins);
		
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
		return logdet;
	}
	
	
	public String usercheck(String email)
	{  
		Registrationpojo check=null;
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
		     Query query=session.createQuery("from Registrationpojo where email=:email");
		     query.setParameter("email",email);
		    check =(Registrationpojo)query.uniqueResult();
		    tx.commit();
		    if(check==null)
		    	return "false";
		    	
		    
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
		
		return "true";
	}
	
	@SuppressWarnings("unchecked")
	public static  void retrieve()
	{
		JSONArray jsonobj=new JSONArray();
		
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
		     Query query=session.createQuery("from Registrationpojo");
		     List<Registrationpojo> reglist=query.list();
		     tx.commit();
		     for(Registrationpojo u:reglist)
		     {
		    	 JSONObject jobj=new JSONObject();
				String name=u.getName();
				String email=u.getEmail();
				String tagline=u.getTagline();
				String bio=u.getBioinfo();
				int con=u.getCoins();
				String skills=u.getSkills();
				jobj.put("name", name);
				jobj.put("email", email);
				jobj.put("tagline", tagline);
				jobj.put("bio", bio);
				jobj.put("con", con);
				jobj.put("skills", skills);
				jsonobj.add(jobj);
		     }
				
		}
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
		}
		finally
		{
		session.close();
			
		}
		
		
		
	}
	
	
	
	
}


