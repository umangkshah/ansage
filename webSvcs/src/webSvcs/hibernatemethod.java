package webSvcs;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;

public class hibernatemethod {

    private static SessionFactory sessionfactory;
	
	
	public void registration()
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
		
		
		hibernatepojo reg=new hibernatepojo();
		reg.setCountry("USA");
		reg.setPassword("usacoun");
		reg.setEmail("uaewef@gmail.com");
		reg.setName("John");
		reg.setProfileid(9);
		reg.setBirthdate(new Date());
		
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
		tx = session.beginTransaction();
		session.save(reg);
		tx.commit();
		}
		catch(Exception e)
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
		
	}
	
	@SuppressWarnings("deprecation")
	public  hibernatepojo login(String email)
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
		
		
		
		
		Session session=sessionfactory.openSession();
		Transaction tx=null;
		try
		{
			tx=session.beginTransaction();
		     @SuppressWarnings("rawtypes")
			Query query=session.createQuery("from hibernatepojo where email=:email");
		    query.setParameter("email",email);
		   
		    
		     List<hibernatepojo> reg=(List<hibernatepojo>)query.list();
		     if(!reg.isEmpty())
		     {
		    for(hibernatepojo u:reg)
		    {	 
		    
		     return u;
		     }
		     }
		     else
		     {
		    	 return null;
		    	 
		     }
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
		return null;
	}
	
}