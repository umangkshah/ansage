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
			Query query=session.createQuery("from Bidpojo where bidid=:bidid");
			query.setParameter("bidid",bidid);
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
			Query query=session.createQuery("DELETE FROM Transpojo WHERE bidid=:bidid");
			query.setParameter("bidid", bidid);
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
			Query query=session.createQuery("from Transpojo WHERE oid=:oid");
			query.setParameter("oid", oid);
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
		sessionfactory.close();
		return jsonlist;
		
	}
	
	public String checkout(String ownerid)
	{
		Session session=null;
		Transaction tx=null;
		int sum=0;
		List<Transpojo> translist=new ArrayList<Transpojo>();
		int oid=Integer.parseInt(ownerid);
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
			Query query=session.createQuery("from Transpojo WHERE oid=:oid");
			query.setParameter("oid", oid);
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
		for(int i=0;i<bidoffer.size();i++)
		{
			sum=sum+(Integer)bidoffer.get(i);
			
		}
			ArrayList qtylist=colqty(translist);
			if(qtylist==null)
				return null;
			int coin=ownercoins(oid);
			if(coin==-1)
				return null;
			int balance=coin-sum;
			if(balance<0)
				return null;
			ArrayList coinlist=colcoins(reid);
			
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
					Query query=session.createQuery("UPDATE Registrationpojo SET coins=coins WHERE profileid=profileid");
					query.setParameter("coins",coins);
					query.setParameter("profileid",profileid);
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
					
				sessionfactory.close();
				return "true";
					
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
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Exception ex)
		{
			return null;
		}
		for(Transpojo td:translist)
		{
			int bidid=td.getBidid();
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Bidpojo where bidid=:bidid");
				query.setParameter("bidid",bidid);
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
		sessionfactory.close();
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
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Exception ex)
		{
			return -1;
		}
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Registrationpojo WHERE profileid=:profileid");
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
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Exception ex)
		{
			return null;
		}
		for(int i=0;i<reid.size();i++)
		{
			int profileid=(Integer)reid.get(i);
			try
			{
				session=sessionfactory.openSession();
				tx=session.beginTransaction();
				Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
				query.setParameter("profileid",profileid);
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
		sessionfactory.close();
		return coinlist;
	}
}

