package webSvcs;

import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.Session;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.LogicalExpression;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;

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
		que.setBidcount(0);
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
		sessionfactory = HibernateUtil.getSessionFactory();
		
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
		
		return queslist;
		
		
	}
	
	public JSONObject retrievequs(int qid)
	{
		Session session=null;
		Transaction tx=null;
		String name=null;
		Questionpojo qus=new Questionpojo();
		sessionfactory = HibernateUtil.getSessionFactory();
		
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
	

	public JSONArray searchlist( ArrayList<String> question)
	{
		Session session=null;
		Transaction tx=null;
		String addt=null;
		ArrayList<String> slist=new ArrayList();
		if(question.size()==1)
		{
				String s=(String)question.get(0);
				slist.add(s);
		}
		else
		{
			for(String s:question)
			{
				if(s=="working"||s=="starting"||s=="running")
				{
					addt=s;
				}
				else
				slist.add(s);
			}
		}
			List<Questionpojo> results=new ArrayList<Questionpojo>();
		JSONArray jsonlist=new JSONArray();
		sessionfactory = HibernateUtil.getSessionFactory();
		for(int i=0;i<slist.size();i++)
				{
					try
					{
					
					session=sessionfactory.openSession();
					tx=session.beginTransaction();
					Criteria cr=session.createCriteria(Questionpojo.class);
					Criteria cr1=session.createCriteria(Questionpojo.class);
					Criteria cr3=session.createCriteria(Questionpojo.class);

					List<Questionpojo> result=new ArrayList<Questionpojo>();
					List<Questionpojo> result1=new ArrayList<Questionpojo>();
					List<Questionpojo> result2=new ArrayList<Questionpojo>( );
					String qus=(String)slist.get(i);
				  Criterion qust=Restrictions.like("qus","%"+qus+"%"+addt+"%",MatchMode.ANYWHERE);
				  Criterion desc=Restrictions.like("descr","%"+qus+"%"+addt+"%",MatchMode.ANYWHERE);
				  LogicalExpression orExp = Restrictions.or(qust, desc);
				  cr.add(orExp);
				  result=cr.list();
				  if(!result.isEmpty())
				  {
					for(int j=0;j<result.size();j++)
						{
							
							if(!results.contains(result.get(j)))
							results.add(result.get(j));
						}
				}
					
				qust=Restrictions.like("qus","%"+addt+"%"+qus+"%",MatchMode.ANYWHERE);
				desc=Restrictions.like("descr","%"+addt+"%"+qus+"%",MatchMode.ANYWHERE);
				orExp = Restrictions.or(qust, desc);
				cr1.add(orExp);
				result1=cr1.list();
				
				if(!result1.isEmpty())
				{
					for(int j=0;j<result1.size();j++)
					{
						
						if(!results.contains(result1.get(j)))
						results.add(result1.get(j));
					}
					
					
				}
				
				 qust=Restrictions.like("qus","%"+qus+"%",MatchMode.ANYWHERE);
				 desc=Restrictions.like("descr","%"+qus+"%",MatchMode.ANYWHERE);
				 orExp=Restrictions.or(qust,desc);
				 cr3.add(orExp);
				  result2=cr3.list();
				 if(!result2.isEmpty())
					{
						
						for(int j=0;j<result2.size();j++)
						{
							if(!results.contains(result2.get(j)))
								results.add(result2.get(j));
						}
					}
						
				}
				  catch(HibernateException e)
					{
				    return null;
					}
				finally
				{
					session.close();
				}
				}
		
		
		
				
				for(int i=0;i<results.size();i++)
				{
					
				Questionpojo qu=(Questionpojo)results.get(i);
					int profileid=qu.getOwnerid();
					Registrationpojo rd=null;
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
				    return null;
					}
					finally
					{
					session.close();
					}
					JSONObject jon=new JSONObject();
					jon.put("coins",rd.getCoins());
					jon.put("oname",rd.getName());
					jon.put("bidcount",qu.getBidcount());
					jon.put("oid",qu.getOwnerid());
					jon.put("mainQ",qu.getQus());
					jon.put("descrQ",qu.getDescr());
					jon.put("category",qu.getCatg());
					jsonlist.add(jon);
				}
				return jsonlist;
				
				}
	
}
				
			
	
	
	
	
	

