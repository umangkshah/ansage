package webSvcs;                 

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

public class Profile {
	
	
	private static SessionFactory sessionfactory;
	
	
	

	public  hibernatepojo getfullprofile(String id)
{
		
		
	 hibernatepojo prof=null;
	int profileid=Integer.parseInt(id);
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
		tx = session.beginTransaction();
		@SuppressWarnings("rawtypes")
	     Query query=session.createQuery("from hibernatepojo where profileid=:profileid");
		query.setParameter("profileid",profileid);
		prof=(hibernatepojo)query.uniqueResult();
		if(prof==null)
			return null;
		
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
	 return prof;
		
	}

}
