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
		catch(Throwable ex)
		{
			System.err.println("Failed to create sessionFactory object." + ex);
			throw new ExceptionInInitializerError(ex);
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

}
