package example.hibernate;

import org.apache.logging.log4j.Logger;

import org.apache.logging.log4j.LogManager;

import java.util.List;
import java.util.ArrayList;
import java.util.Iterator;
import org.hibernate.*;
import org.hibernate.cfg.Configuration;

import ats.util.HibernateDAOProvider;

import javax.persistence.*;

import example.util.*;

public class CandidateManager {
	
	static final Logger logger = LogManager.getLogger(CandidateManager.class.getName());
	List candidates = new ArrayList();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		CandidateManager hw = new CandidateManager();
		hw.addCandidate();
		//hw.listCandidates();
	}

	public void listCandidates()
	{
		Session session = HibernateExampleUtil.getSessionFactory().openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			List candidates = session.createQuery("from Candidate c order by c.firstName").getResultList();
			for (Iterator iter = candidates.iterator(); iter.hasNext();)
			{
					Candidate loadedCandidate = (Candidate) iter.next();
					logger.info(loadedCandidate.getFirstName() + " " + loadedCandidate.getLastName() + " / " + loadedCandidate.getEmail());
			}
			session.flush();
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
	}
	
	public List getCandidates() {
		Session session = HibernateDAOProvider.getSessionFactory().openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			List list = session.createQuery("from Candidate c order by c.firstName").getResultList();
			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
					Candidate loadedCandidate = (Candidate) iter.next();
					candidates.add(loadedCandidate.getFirstName() + " " + loadedCandidate.getLastName());
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

	
	public boolean addCandidate()
	{
		Session session = HibernateExampleUtil.getSessionFactory().openSession();
		Transaction tx = null;
		boolean result=false;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			Candidate candidate = new Candidate();
			candidate.setFirstName("Rohit");
			candidate.setLastName("Lal");
			candidate.setGender(new Character('M'));
			candidate.setEmail("rohit.lal@hogwarts.com");
			Integer candidateId = (Integer)session.save(candidate);
			session.flush();
			tx.commit();
			result=true;
		}
		catch(HibernateException he)
		{
			result=false;
			if(tx!=null){
				tx.rollback();
				he.printStackTrace();
			}
		}
		finally
		{
			session.close();
		}
		return result;
	}

}
