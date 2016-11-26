package webSvcs;

import org.hibernate.cfg.Configuration;
import java.util.*;
import org.hibernate.*;
import org.json.simple.*;

public class AnswerClass {
	
	private static SessionFactory sessionfactory;
	
	
/*public static void main(String [] args)
	{
		JSONObject jon=new JSONObject();
		jon.put("qid", "1");
		jon.put("reqid", "1");
		//jon.put("answer","fggfgf");
		AnswerClass an=new AnswerClass();
		Answerpojo k=an.viewans(jon);
		System.out.println(k.getAnswer());
		
	}*/
	
	
	
	public String givepermission(JSONObject permjson)
	{
		Session session=null;
		Transaction tx=null;
	    try
		{
		sessionfactory=new Configuration().configure().buildSessionFactory();
		}
	catch(Exception ex)
		{
		return null;
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
		return null;
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

public Answerpojo viewans(JSONObject jon)
{
	
	
	String qd=jon.get("qid").toString();
	int qid=Integer.parseInt(qd);
	String reqd=jon.get("reqid").toString();
	int reqid=Integer.parseInt(reqd);
	Session session=null;
	Transaction tx=null;
	Answerpojo ans=new Answerpojo();
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
		Query query=session.createQuery("from Answerpojo WHERE qid=:qid and reqid=:reqid");
		query.setParameter("qid",qid);
		query.setParameter("reqid", reqid);
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
		sessionfactory.close();
		return ans;
	}

/*public void transaction(JSONObject jon)
{
	Session session=null;
	Transaction tx=null;
	Bidpojo biddet=new Bidpojo();
	Registrationpojo regdet=new Registrationpojo();
	String qd=jon.get("qid").toString();
	int qid=Integer.parseInt(qd);
	String reqd=jon.get("reqid").toString();
	int reqid=Integer.parseInt(reqd);
	try
	{
		sessionfactory=new Configuration().configure().buildSessionFactory();

	}
	catch(Exception ex)
	{
		//return null;
	}
	try
	{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("from Bidpojo WHERE qid=:qid and reqid=:reqid");
		query.setParameter("qid",qid);
		query.setParameter("reqid",reqid);
		biddet=(Bidpojo)query.uniqueResult();
		tx.commit();
		//if(biddet==null)
			//return null;
	}
	catch(HibernateException e)
	{
		if(tx!=null)
			tx.rollback();
			//return null;
	}
	finally
	{
		session.close();
	}
	int offer=biddet.getOffer();
	int profileid=reqid;
	try
	{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("from Registrationpojo WHERE profileid=:profileid");
		query.setParameter("profileid",profileid);
		regdet=(Registrationpojo)query.uniqueResult();
		tx.commit();
		//if(regdet==null)
			//return null;
	}
	catch(HibernateException e)
	{
		if(tx!=null)
		tx.rollback();
			//return null;
	}
	finally
	{
		session.close();
	}
	int coins=regdet.getCoins();
	coins=coins+offer;
	try
	{
		session=sessionfactory.openSession();
		tx=session.beginTransaction();
		Query query=session.createQuery("UPDATE Registrationpojo SET coins=:coins WHERE profileid=:profileid");
		query.setParameter("coins",coins);
		query.setParameter("profileid", profileid);
		int result=query.executeUpdate();
		tx.commit();
		//if(result==0)
			//return null;
	}
	catch(HibernateException e)
	{
		if(tx!=null)
		tx.rollback();
				//return null;
	}
	finally
	{
		session.close();
	}
	
}*/
	
}











