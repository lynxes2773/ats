package ats.data;

import java.util.Iterator;
import java.util.List;
import java.util.ArrayList;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Bean;

import javax.sql.DataSource;

import example.hibernate.Candidate;
import org.springframework.stereotype.Repository;

@Repository("hibernateDAOProvider")
public class DAOProviderHibernateImpl implements DAOProvider{
	static final Logger logger = LogManager.getLogger(DAOProviderHibernateImpl.class.getName());
	private SessionFactory sessionFactory;
	
	List candidates = new ArrayList();

	@Autowired
	public DAOProviderHibernateImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	public List getCandidates() {

//		Session session = HibernateSessionProvider.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
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
//		Session session = HibernateSessionProvider.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
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
//		Session session = HibernateSessionProvider.getSessionFactory().openSession();
		Session session = sessionFactory.openSession();
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
