package webSvcs;

import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;
import org.json.simple.*;

public class AnswerClass {
	
	private static SessionFactory sessionfactory;
	
	
/*	public static void main(String [] args)
	{
		JSONObject jon=new JSONObject();
		jon.put("qid", "1");
		jon.put("reqid", "1");
		jon.put("answer","fggfgf");
		AnswerClass an=new AnswerClass();
		String k=an.saveans(jon);
		System.out.println(k);
		
	}*/
	
	
	
	public String givepermission(JSONObject permjson)
	{
		Session session=null;
		Transaction tx=null;
	    try
		{
		sessionfactory=new Configuration().configure().buildSessionFactory();
		}
	catch(Throwable ex)
		{
		System.err.println("Failed to create sessionFactory object." + ex);
		throw new ExceptionInInitializerError(ex);
		}
		String qd=permjson.get("qid").toString();
		int qid=Integer.parseInt(qd);
		String rqid=permjson.get("reqid").toString();
		int reqid=Integer.parseInt(rqid);
		String permissionvalue="true";
		try
		{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("UPDATE Permissionpojo SET permissionvalue=:permissionvalue WHERE qid=:qid AND reqid=:reqid");
		query.setParameter("qid",qid);
		query.setParameter("reqid",reqid);
		query.setParameter("permissionvalue",permissionvalue);
		query.executeUpdate();
		tx.commit();
		}
		catch(HibernateException e)
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



public String saveans(JSONObject ans)
{
	Session session=null;
	Transaction tx=null;
	try
	{
		sessionfactory=new Configuration().configure().buildSessionFactory();

	}
	catch(Exception ex)
	{
		System.err.println("Failed to create sessionFactory object." + ex);
		throw new ExceptionInInitializerError(ex);
	}
	String qd=ans.get("qid").toString();
	int qid=Integer.parseInt(qd);
	String reqd=ans.get("reqid").toString();
	int reqid=Integer.parseInt(reqd);
	String answer=ans.get("answer").toString();
	try
	{   session=sessionfactory.openSession();
		tx=session.beginTransaction();
	     @SuppressWarnings("rawtypes")
	     Query query=session.createQuery("from Permissionpojo where qid=:qid and reqid=:reqid");
	     query.setParameter("qid",qid);
	     query.setParameter("reqid",reqid);
	     Permissionpojo perm=(Permissionpojo)query.uniqueResult();
	     if(perm.getPermissionvalue()=="false")
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
	     @SuppressWarnings("rawtypes")
	     Answerpojo an=new Answerpojo();
	     an.setAnswer(answer);
	     an.setQid(qid);
	     an.setReqid(reqid);
	     session.save(an);
	     tx.commit();
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
	sessionfactory.close();
	return "true";
	
	}
}
