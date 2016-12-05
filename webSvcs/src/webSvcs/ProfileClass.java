package webSvcs;                 

import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.stat.Statistics;
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
		sessionfactory = HibernateUtil.getSessionFactory();

		try
			{
			 session=sessionfactory.openSession();
			 tx=null;
			tx = session.beginTransaction();
			@SuppressWarnings("rawtypes")
			Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
			query.setCacheable(true);
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
			System.out.println(HibernateUtil.getSessionFactory().getStatistics().getEntityFetchCount()); 
	           System.out.println(HibernateUtil.getSessionFactory().getStatistics().getSecondLevelCacheHitCount());
			
			
			
			
			}
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
		sessionfactory = HibernateUtil.getSessionFactory();
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
		    query.setCacheable(true);
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
		return "true";
	}
	
	public String getcoins(String owid)
	{
		Session session=null;
		Transaction tx=null;
		String coins=null;
		int profileid=Integer.parseInt(owid);
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
			query.setParameter("profileid",profileid);
			query.setCacheable(true);
			Registrationpojo rd=(Registrationpojo)query.uniqueResult();
			tx.commit();
			int coin=rd.getCoins();
			coins=String.valueOf(coin);
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
		return coins;
		
		
		
	}
	
	
}
	
	

