package ats.biz;

import java.util.Iterator;


import java.util.List;
import java.util.ArrayList;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import example.hibernate.Candidate;
import example.hibernate.CandidateManager;
import ats.util.HibernateUtil;

public class ApplicationManager {
	static final Logger logger = LogManager.getLogger(ApplicationManager.class.getName());
	
	List candidates = new ArrayList();

	public static void main(String[] args) {
		ApplicationManager hw = new ApplicationManager();
	}


	public List getCandidates() {
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			List list = session.createQuery("from Candidate c order by c.lastName").getResultList();
			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
					Candidate loadedCandidate = (Candidate) iter.next();
					//candidates.add(loadedCandidate.getFirstName() + " " + loadedCandidate.getLastName());
					candidates.add(loadedCandidate);
			}
			tx.commit();
		}
		catch(HibernateException he)
		{
			if(tx!=null){
				tx.rollback();
				he.printStackTrace();
			}
		}
		finally
		{
			session.close();
		}

		return candidates;
	}

	public void setCandidates(List candidates) {
		this.candidates = candidates;
	}
	
	public Candidate getCandidate(Integer candidateId)
	{
		Candidate candidate = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			List list = session.createQuery("from Candidate c where c.candidateId="+candidateId.toString()).getResultList();
			
			if(list!=null)
			{	
				candidate = (Candidate)list.get(0);
			}
			tx.commit();
		}
		catch(HibernateException he)
		{
			if(tx!=null){
				tx.rollback();
				he.printStackTrace();
			}
		}
		finally
		{
			session.close();
		}
		return candidate;
	}
	
	public Integer addCandidate(Candidate candidate)
	{
		Integer candidateId = null;
		Session session = HibernateUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			candidateId = (Integer)session.save(candidate);
			tx.commit();
		}
		catch(HibernateException he)
		{
			if(tx!=null){
				tx.rollback();
				he.printStackTrace();
			}
		}
		finally
		{
			session.close();
		}
		return candidateId;		
	}



}
