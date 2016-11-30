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



public class QuestionClass {
	
	 private static SessionFactory sessionfactory;
	 
	public String quesdetails(JSONObject jsonobj)
	{
		
		Session session=null;
		Registrationpojo quid=null;
		String sqd;
		int id;
		try
		{
		 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Exception ex)
		{
			return null;
		}
		Questionpojo que=new Questionpojo();
		String owid=jsonobj.get("ownerid").toString();
		int profileid=Integer.parseInt(owid);
		String catg=jsonobj.get("category").toString();
		String mainques=jsonobj.get("mainQ").toString();
		String descques=jsonobj.get("descrQ").toString();
		que.setOwnerid(profileid);
		que.setCatg(catg);
		que.setDescr(descques);
		que.setQus(mainques);
		Transaction tx=null;
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Registrationpojo where profileid=:profileid");
			query.setParameter("profileid",profileid);
			quid=(Registrationpojo)query.uniqueResult();
			tx.commit();
			if(quid==null)
			return null;
			
		}
		catch(Exception e)
		{
			if(tx!=null)
			tx.rollback();
			return null;
		}
		try
		{
		session=sessionfactory.openSession();
		tx = session.beginTransaction();
		 id=(Integer)session.save(que);
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
        sqd=String.valueOf(id);
		
		sessionfactory.close();
		return sqd;
		}
	
	public List<Questionpojo> displayqus()
	{
		
		Session session=null;
		Transaction tx=null;
		List<Questionpojo> queslist=null;
		try
		{
			 sessionfactory=new Configuration().configure().buildSessionFactory();
		}
		catch(Exception e)
		{
			return null;
		}
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("FROM Questionpojo");
			query.setMaxResults(5);
			queslist=query.list();
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
		sessionfactory.close();
		return queslist;
		
		
	}
	
	public JSONObject retrievequs(int qid)
	{
		Session session=null;
		Transaction tx=null;
		String name=null;
		Questionpojo qus=new Questionpojo();
		try
		{
			 sessionfactory=new Configuration().configure().buildSessionFactory();

		}
		catch(Exception e)
		{
			return null;
			
		}
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("from Questionpojo where qid=:qid");
			query.setParameter("qid",qid);
			qus=(Questionpojo)query.uniqueResult();
			tx.commit();
			if(qus==null)
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
		int profileid=qus.getOwnerid();
		try
		{
			session=sessionfactory.openSession();
			tx=session.beginTransaction();
			Query query=session.createQuery("SELECT name from Registrationpojo where profileid=:profileid");
			query.setParameter("profileid",profileid);
			 name=(String)query.uniqueResult();
			tx.commit();
			if(name==null)
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
		JSONObject jon=new JSONObject();
		jon.put("qid",qid);
		jon.put("question",qus.getQus());
		jon.put("descr", qus.getDescr());
		jon.put("oid",qus.getOwnerid());
		jon.put("oname",name);
		return jon;
		
		
		
	}
	
	
	
}
