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

public class TransClass {
	
	private static SessionFactory sessionfactory;
	
	public String inscart(String bdid)
	{
		Session session=null;
		Transaction tx=null;
		Bidpojo bd=null;
		Transpojo td=new Transpojo();
		Transpojo md=new Transpojo();
		Questionpojo qd=null;
		int qid=0;
		int bidid=Integer.parseInt(bdid);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Bidpojo where bidid=:bidid");
			query.setParameter("bidid",bidid);
			query.setCacheable(true);
			bd=(Bidpojo)query.uniqueResult();
			if(bd==null)
			return null;
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
			Query query=session.createQuery("from Transpojo where bidid=:bidid");
			query.setParameter("bidid",bidid);
			query.setCacheable(true);
			td=(Transpojo)query.uniqueResult();
			if(td!=null)
			return null;
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
			qid=bd.getQid();
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Questionpojo where qid=:qid");
			query.setParameter("qid",qid);
			query.setCacheable(true);
			qd=(Questionpojo)query.uniqueResult();
			if(qd==null)
				return null;
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
		int reqid=bd.getReqid();
		int ownerid=qd.getOwnerid();
		md.setBidid(bidid);
		md.setQid(qid);
		md.setReqid(reqid);
		md.setOid(ownerid);
		md.setQty(1);
		try
		{
			qid=bd.getQid();
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			session.save(md);
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
		
		return "true";
		
	}
	
	public String removecart(String bdid)
	{
		Session session=null;
		Transaction tx=null;
		int bidid=Integer.parseInt(bdid);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("DELETE FROM Transpojo WHERE bidid=:bidid");
			query.setParameter("bidid", bidid);
			query.setCacheable(true);
			query.executeUpdate();
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
		return "true";
		
	}
	
	public List<JSONObject> retcart(String ownerid)
	{
		Session session=null;
		Transaction tx=null;
		List<Transpojo> translist=new ArrayList<Transpojo>();
		List<JSONObject>jsonlist=new ArrayList<JSONObject>();
		Bidpojo bd=new Bidpojo();
		Registrationpojo rd=new Registrationpojo();
		int oid=Integer.parseInt(ownerid);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Transpojo WHERE oid=:oid");
			query.setParameter("oid", oid);
			query.setCacheable(true);
			translist=(List<Transpojo>)query.list();
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
		for(Transpojo td:translist)
		{
			JSONObject json=new JSONObject();
			int bidid=td.getBidid();
			int profileid=td.getReqid();
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Bidpojo WHERE bidid=:bidid");
				query.setParameter("bidid", bidid);
				query.setCacheable(true);
				bd=(Bidpojo)query.uniqueResult();
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
			int offer=bd.getOffer();
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Registrationpojo WHERE profileid=:profileid");
				query.setParameter("profileid", profileid);
				query.setCacheable(true);
				rd=(Registrationpojo)query.uniqueResult();
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
			String name=rd.getName();
			json.put("bidid",td.getBidid());
			json.put("qty", td.getQty());
			json.put("reqid",td.getReqid());
			json.put("rname",rd.getName());
			json.put("offer",bd.getOffer());
			jsonlist.add(json);
		}
		
		return jsonlist;
		
	}
	
	public ArrayList checkout(String ownerid)
	{
		Session session=null;
		Transaction tx=null;
		int sum=0;
		List<Transpojo> translist=new ArrayList<Transpojo>();
		int oid=Integer.parseInt(ownerid);
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Transpojo WHERE oid=:oid");
			query.setParameter("oid", oid);
			query.setCacheable(true);
			translist=(List<Transpojo>)query.list();
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
		
		ArrayList reid=colreq(translist);
		if(reid==null)
			return null;
		ArrayList bidoffer=coloffer(translist);
		if(bidoffer==null)
			return null;
		
			ArrayList qtylist=colqty(translist);
			for(int i=0;i<bidoffer.size();i++)
			{
				int offervalue=(Integer)bidoffer.get(i);
				int qtyvalue=(Integer)qtylist.get(i);
				sum=sum+(offervalue*qtyvalue);
			}
			
			if(qtylist==null)
				return null;
			int coin=ownercoins(oid);
			if(coin==-1)
				return null;
			int balance=coin-sum;
			if(balance<0)
				return null;
			
		ArrayList coinlist=colcoins(reid);
			ArrayList emaillist=colemail(reid);
			if(emaillist==null)
				return null;
			
			try
			{ 	int profileid=oid;
				int coins=balance;
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("UPDATE Registrationpojo SET coins=:coins where profileid=:profileid");
				query.setParameter("coins",coins);
				query.setParameter("profileid",profileid);
				query.setCacheable(true);
				query.executeUpdate();
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
			for(int i=0;i<reid.size();i++)
			{
				int offer=(Integer)bidoffer.get(i);
				int quantity=(Integer)qtylist.get(i);
				int coins=(Integer)coinlist.get(i);
				int profileid=(Integer)reid.get(i);
				coins=coins+offer*quantity;
				try
				{
					session=sessionfactory.openSession();
					tx=session.beginTransaction();
					Query query=session.createQuery("UPDATE Registrationpojo SET coins=:coins WHERE profileid=:profileid");
					query.setParameter("coins",coins);
					query.setParameter("profileid",profileid);
					query.setCacheable(true);
					query.executeUpdate();
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
			}
			String delbd=deletebids(translist);
			if(delbd==null)
				return null;
				String check=delbid(oid);
				if(check==null)
					return null;
				
				
				return emaillist;
					
		}
			
	public ArrayList colreq(List<Transpojo> translist)
	{
		ArrayList reid=new ArrayList();
		for(Transpojo td:translist)
		{
			int reqid=td.getReqid();
			reid.add(reqid);
			
		}
	return reid;
	}
	public ArrayList coloffer(List<Transpojo> translist)
	{
		Session session=null;
		Transaction tx=null;
		ArrayList bidoffer=new ArrayList();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		for(Transpojo td:translist)
		{
			int bidid=td.getBidid();
			
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Bidpojo where bidid=:bidid");
				query.setParameter("bidid",bidid);
				query.setCacheable(true);
				Bidpojo bd=(Bidpojo)query.uniqueResult();
				tx.commit();
				int offer=bd.getOffer();
				bidoffer.add(offer);
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
		}
	return bidoffer;
	}
	public ArrayList colqty(List<Transpojo> translist)
	{
		ArrayList qtylist=new ArrayList();
		for(Transpojo td:translist)
		{
			int qty=td.getQty();
			qtylist.add(qty);
			
		}
	return qtylist;
	}
	
	public int ownercoins(int oid)
	{
		Session session=null;
		Transaction tx=null;
		int profileid=oid;
		int coins=0;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Registrationpojo WHERE profileid=:profileid");
			query.setCacheable(true);
			query.setParameter("profileid",profileid);
			Registrationpojo rd=(Registrationpojo)query.uniqueResult();
			 coins=rd.getCoins();
		}
		catch(HibernateException e)
		{
			if(tx!=null)
			tx.rollback();
			return -1;
		}
		finally
		{
			session.close();
		}
		return coins;
		
	}
	
	public ArrayList colcoins(ArrayList reid)
	{
		Session session=null;
		Transaction tx=null;
		ArrayList coinlist=new ArrayList();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		for(int i=0;i<reid.size();i++)
		{
			int profileid=(Integer)reid.get(i);
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
				query.setParameter("profileid",profileid);
				query.setCacheable(true);
				Registrationpojo rd=(Registrationpojo)query.uniqueResult();
				tx.commit();
				int coins=rd.getCoins();
				coinlist.add(coins);
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
		}
		
		return coinlist;
	}
	
	public ArrayList colemail(ArrayList reid)
	{
		Session session=null;
		Transaction tx=null;
		ArrayList emaillist=new ArrayList();
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		for(int i=0;i<reid.size();i++)
		{
			int profileid=(Integer)reid.get(i);
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
				query.setParameter("profileid",profileid);
				query.setCacheable(true);
				Registrationpojo rd=(Registrationpojo)query.uniqueResult();
				tx.commit();
				String mail=rd.getEmail();
				emaillist.add(mail);
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
		}
		
		return emaillist;
	}
	public String delbid(int oid)
	{
		Session session=null;
		Transaction tx=null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("DELETE FROM Transpojo where oid=:oid");
			query.setParameter("oid",oid);
			query.setCacheable(true);
			int m=query.executeUpdate();
			if(m==0)
				return null;
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
		
		return "true";
	}
	
	public String deletebids(List<Transpojo> translist)
	{
		
		Session session=null;
		Transaction tx=null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		for(Transpojo td:translist)
			{
				int bidid=td.getBidid();
				try
				{
					session=sessionfactory.openSession();
					tx=session.beginTransaction();
					Query query=session.createQuery("DELETE FROM Bidpojo where bidid=:bidid");
					query.setParameter("bidid",bidid);
					query.setCacheable(true);
					int m=query.executeUpdate();
					if(m==0)
						return null;
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
			}
		return "true";
	}
	
	public String updateqty(JSONArray arrayobj)
	{
		Session session=null;
		Transaction tx=null;
		SessionFactory sessionFactory = HibernateUtil.getSessionFactory();
		Iterator i = arrayobj.iterator();
		while(i.hasNext())
		{
			
			JSONObject jon=(JSONObject)i.next();
			int bidid=Integer.parseInt(jon.get("bidid").toString());
			int qty=Integer.parseInt(jon.get("qty").toString());
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("UPDATE Transpojo set qty=:qty where bidid=:bidid");
				query.setParameter("qty", qty);
				query.setParameter("bidid", bidid);
				query.setCacheable(true);
				int m=query.executeUpdate();
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
		}
		return "true";
			
	}
			
			
}
		
		
		
		
		
		
		
		
		
	
	
	
	
	


