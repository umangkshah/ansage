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
		sessionfactory = HibernateUtil.getSessionFactory();
		
		int retid;
		String reid=null;
		String id=biddata.get("qid").toString();
		int qid=Integer.parseInt(id);
		String rqid=biddata.get("reqid").toString();
		int profileid=Integer.parseInt(rqid);
		String coins=biddata.get("offer").toString();
		int offer=Integer.parseInt(coins);
		Bidpojo bid=new Bidpojo();
		bid.setQid(qid);
		bid.setReqid(profileid);
		bid.setOffer(offer);
		Session session=null;
		Transaction tx=null;
		Questionpojo md=null;
		int reqid=profileid;
		try
		{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("from Bidpojo WHERE qid=:qid AND reqid=:reqid");
		query.setCacheable(true);
		query.setParameter("qid",qid);
		query.setParameter("reqid",reqid);
        tx.commit();
		Bidpojo qd=(Bidpojo)query.uniqueResult();
		if(qd!=null)
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
		try
		{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
		query.setParameter("profileid",profileid);
		query.setCacheable(true);
		tx.commit();
		
		Registrationpojo qd=(Registrationpojo)query.uniqueResult();
		if(qd==null)
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
		try
			{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Questionpojo where qid=:qid");
			query.setParameter("qid",qid);
			query.setCacheable(true);
			tx.commit();
			 md=(Questionpojo)query.uniqueResult();
			if(md==null)
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
		try
		{ 
			session=sessionfactory.openSession();
			int bidcount=md.getBidcount();
			bidcount=bidcount+1;
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("UPDATE Questionpojo set bidcount=:bidcount where qid=:qid");
			query.setParameter("bidcount",bidcount);
			query.setParameter("qid",qid);
			query.executeUpdate();
			query.setCacheable(true);
			tx.commit();
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
		
		
		try
			{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			 retid=(int)session.save(bid);
			  reid=String.valueOf(retid);
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
		
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Permissionpojo perm=new Permissionpojo();
			perm.setQid(qid);
			perm.setReqid(reqid);
			perm.setPermissionvalue("false");
			session.save(perm);
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
			
		
		return reid; 
		}
	
		public List<JSONObject> retbids(String id)
			{
			int qid=Integer.parseInt(id);
			List<Bidpojo> bidlist=new ArrayList<Bidpojo>();
			List<JSONObject> jsonlist=new ArrayList<JSONObject>();
			Registrationpojo reg=null;
			sessionfactory = HibernateUtil.getSessionFactory();
			
			Session session=null;
			Transaction tx=null;
			try
				{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Bidpojo where qid=:qid");
				query.setParameter("qid",qid);
				 bidlist=query.list();
				 query.setCacheable(true);
				 tx.commit();
				 if(bidlist==null)
					 return null;
				
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
			for(Bidpojo b:bidlist)
				{
				JSONObject json=new JSONObject();
				 int profileid=b.getReqid();
				try
				 {
					 session=sessionfactory.openSession();
					 tx=session.beginTransaction();
					 Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
					 query.setParameter("profileid", profileid);
					 query.setCacheable(true);
					 reg=(Registrationpojo)query.uniqueResult();
					 tx.commit();
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
				 
				json.put("qid",b.getQid());
				json.put("reqid", b.getReqid());
				json.put("offer",b.getOffer());
				json.put("name", reg.getName());
				json.put("coins",reg.getCoins());
				json.put("skills",reg.getSkills());
				json.put("bidid",b.getBidid());
				jsonlist.add(json);
				
				}
			
			return jsonlist;
			
			
			
			
			
			}
}
	
	
	
	
	
	
	
	


