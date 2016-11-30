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
	
	

}
