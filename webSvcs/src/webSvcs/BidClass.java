package webSvcs;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;
import org.json.simple.*;

public class BidClass 
	{
	
	private static SessionFactory sessionfactory;
	
	/*public static void main(String[]args)
	{
		JSONObject jon=new JSONObject();
		jon.put("qid","4");
		jon.put("reqid","2");
		jon.put("offer", "67");
		BidClass bi=new BidClass();
		String check=bi.savebid(jon);
		
			System.out.println(check);
		
	}*/
	
	
	public String savebid(JSONObject biddata)
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
		String id=biddata.get("qid").toString();
		int qid=Integer.parseInt(id);
		String reqid=biddata.get("reqid").toString();
		int profileid=Integer.parseInt(reqid);
		String coins=biddata.get("offer").toString();
		int offer=Integer.parseInt(coins);
		Bidpojo bid=new Bidpojo();
		Bididpojo bdid=new Bididpojo();
		bdid.setQid(qid);
		bdid.setReqid(profileid);
		bid.setBidid(bdid);
		bid.setOffer(offer);
		Session session=null;
		Transaction tx=null;
		String reqsid=null;
		try
		{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
		query.setParameter("profileid",profileid);
		tx.commit();
		Registrationpojo qd=(Registrationpojo)query.uniqueResult();
		if(qd==null)
			return "false";
		}
		catch(HibernateException e)
	 	{
		 if(tx!=null)
		 tx.rollback();
		 return "false";
	 	}
		finally
		{
		session.close();
		}
		try
			{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Questionpojo where qid=:qid");
			query.setParameter("qid",qid);
			tx.commit();
			Questionpojo qd=(Questionpojo)query.uniqueResult();
			if(qd==null)
				return "false";
			}
		 catch(HibernateException e)
		 	{
			 if(tx!=null)
			 tx.rollback();
			 return "false";
		 	}
		finally
			{
			session.close();
			}
		try
			{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			session.save(bid);
			tx.commit();
			}
		catch(Exception e)
			{
			if(tx!=null)
			tx.rollback();
			System.out.println(e.getMessage());
			return "false";
			
			}
		finally
			{
			session.close();
			}
			
		sessionfactory.close();
		return "true"; 
		}
	
		public List<Bidpojo> retbids(String id)
			{
			int qid=Integer.parseInt(id);
			List<Bidpojo> bidlist=null;
			try
				{
				sessionfactory=new Configuration().configure().buildSessionFactory();
				}
			catch(Throwable ex)
				{
				System.err.println("Failed to create sessionFactory object." + ex);
				throw new ExceptionInInitializerError(ex);
				}
			Session session=null;
			Transaction tx=null;
			try
				{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Bidpojo where qid=:qid");
				query.setParameter("qid",qid);
				 bidlist=query.list();
				 if(bidlist==null)
					 return null;
				tx.commit();
				}
			catch(Exception e)
				{
				if(tx!=null)
				tx.rollback();
				return null;
				}
			finally
				{
				 session.close();
				}
			return bidlist;
			}
	

	
		
	
	
	
	
	
	
}
	
	
	
	
	
	
	
	


