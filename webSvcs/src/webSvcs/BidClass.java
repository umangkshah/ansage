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
		String coins=biddata.get("offer").toString();
		int offer=Integer.parseInt(coins);
		Bidpojo bid=new Bidpojo();
		bid.setQid(qid);
		bid.setOffer(offer);
		Session session=null;
		Transaction tx=null;
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
			return "false";
			
			}
		finally
			{
			session.close();
			}
		sessionfactory.close();
		return "true"; 
		}
	
		public JSONArray retbids(String id)
			{
			int qid=Integer.parseInt(id);
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
			Session session=null;
			Transaction tx=null;
			try
				{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Bidpojo where qid=:qid");
				query.setParameter("qid",qid);
				List<Bidpojo> bidlist=query.list();
				tx.commit();
				for(Bidpojo bid:bidlist)
					{
					 JSONObject jobj=new JSONObject();
					 int quesid=bid.getQid();
					 int reqid=bid.getReqid();
					 int offer=bid.getOffer();
					 jobj.put("qid",quesid);
					 jobj.put("reqid",reqid);
					 jobj.put("offer",offer);
					 jsonobj.add(jobj);				
					}
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
			return jsonobj;
			}
	}
	
	
	
	
	
	
	
	


