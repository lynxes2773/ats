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

import javax.persistence.Query;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import javax.sql.DataSource;

import example.hibernate.Candidate;
import org.springframework.stereotype.Repository;

import ats.entity.*;

@Repository("hibernateDAOProvider")
public class DAOProviderHibernateImpl implements DAOProvider{
	static final Logger logger = LogManager.getLogger(DAOProviderHibernateImpl.class.getName());
	private SessionFactory sessionFactory;
	
	List candidates = new ArrayList();
	List applications = new ArrayList();
	
	

	@Autowired
	public DAOProviderHibernateImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}	
	
	public List getApplications()
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			List list = session.createQuery("from " + Application.class.getName()).getResultList();
			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
					Application loadedApplication = (Application) iter.next();
					applications.add(loadedApplication);
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

		return applications;
	}
	
	public void setApplications(List applications)
	{
		this.applications=applications;
	}
	
	public Integer addApplication(Application application)
	{
		Integer applicationId = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			application.setApplicationDate(new java.sql.Date(System.currentTimeMillis()));
			applicationId = (Integer)session.save(application);
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
		return applicationId;		
	}
	
	public Application getApplication(Integer applicationId)
	{
		Application application = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from Application a where a.id = :applicationId");
			query.setParameter("applicationId", applicationId);
			
			List list = query.getResultList();
					
			
			if(list!=null)
			{	
				application = (Application)list.get(0);
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
		return application;
	}
	
	public List getCandidates() {

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
	
	
	public List getPositionTypes()
	{
		List<PositionType> positionTypeList = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try
		{
			tx = session.getTransaction();
			tx.begin();
			String query = "from " + PositionType.class.getName();
			positionTypeList = session.createQuery(query).getResultList();
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
		
		return positionTypeList;
	}

	public List getJobSourceTypes()
	{
		List<JobSourceType> jobSourceTypeList = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try
		{
			tx = session.getTransaction();
			tx.begin();
			String query = "from " + JobSourceType.class.getName();
			jobSourceTypeList = session.createQuery(query).getResultList();
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
		
		return jobSourceTypeList;
	}
	
	
	public List getApplicationStatuses()
	{
		List<ApplicationStatusType> statusList = null;
		ApplicationStatusType statusType = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			String query = "from " + ApplicationStatusType.class.getName();
			
			statusList = session.createQuery(query).getResultList();
			
//			CriteriaBuilder criteriaBuilder = session.getCriteriaBuilder();
//			CriteriaQuery<ApplicationStatusType> criteriaQuery = criteriaBuilder.createQuery(ApplicationStatusType.class);
//			Root<ApplicationStatusType> root = criteriaQuery.from(ApplicationStatusType.class);
//			Query query = session.createQuery(criteriaQuery);
//			statusList = query.getResultList();
			
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
		
		return statusList;
	}
	
	public Integer addDummyApplication()
	{
		Integer applicationId = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			applicationId = (Integer)session.save(new DummyApplication());
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
		return applicationId;
	}
}
