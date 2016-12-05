package webSvcs;

import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;
import org.json.simple.*;

public class AnswerClass {
	
	private static SessionFactory sessionfactory;
	
	

	
	
	
	public String givepermission(JSONObject permjson)
	{
		Session session=null;
		Transaction tx=null;
		sessionfactory = HibernateUtil.getSessionFactory();
		
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
		query.setCacheable(true);
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
		
		
		return "true";

	}



public String saveans(JSONObject ans)
{
	Session session=null;
	Transaction tx=null;
	sessionfactory = HibernateUtil.getSessionFactory();
	
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
	     query.setCacheable(true);
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
	
	return "true";
	
	}

public Answerpojo viewans(JSONObject jon)
{
	
	
	String qd=jon.get("qid").toString();
	int qid=Integer.parseInt(qd);
	String reqd=jon.get("reqid").toString();
	int reqid=Integer.parseInt(reqd);
	Session session=null;
	Transaction tx=null;
	Answerpojo ans=new Answerpojo();
	sessionfactory = HibernateUtil.getSessionFactory();
	
	try
	{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("from Answerpojo WHERE qid=:qid and reqid=:reqid");
		query.setParameter("qid",qid);
		query.setParameter("reqid", reqid);
		query.setCacheable(true);
		ans=(Answerpojo)query.uniqueResult();
		tx.commit();
		if(ans==null)
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
		
		return ans;
	}

	
}











