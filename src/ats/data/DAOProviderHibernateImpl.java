package ats.data;

import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.io.InputStream;
import java.sql.Blob;
import java.util.ArrayList;
import java.util.HashMap;

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
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;  

import example.hibernate.Candidate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import ats.entity.*;

@Repository("hibernateDAOProvider")
@Transactional
public class DAOProviderHibernateImpl implements DAOProvider{
	static final Logger logger = LogManager.getLogger(DAOProviderHibernateImpl.class.getName());
	private SessionFactory sessionFactory;
	private JdbcTemplate jdbcTemplate;
	
	List candidates = new ArrayList();
	List applications = new ArrayList();
	
	public void setJdbcTemplate(JdbcTemplate jdbcTemplate)
	{
		this.jdbcTemplate=jdbcTemplate;
	}
	
	@Autowired
	public DAOProviderHibernateImpl(SessionFactory sessionFactory)
	{
		this.sessionFactory = sessionFactory;
	}
	
	@Autowired
	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}
	
	public List getCandidatesByPage(int pageId, int totalPageCount)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.beginTransaction();
			Query query = session.createQuery("from Candidate c order by c.lastName");
			if(pageId==1)
			{
				query.setFirstResult(0);
			}
			else
			{
				query.setFirstResult(pageId-1);
			}
			query.setMaxResults(totalPageCount);
			List list = query.getResultList();
			System.out.println("#### Results: "+list.size());
			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
					Candidate loadedCandidate = (Candidate) iter.next();
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
	
	public List getApplications()
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			applications.clear();
			tx = session.beginTransaction();
			List list = session.createQuery("FROM Application a").getResultList();
			tx.commit();
			Transaction tx1 = session.beginTransaction();
			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
					Application loadedApplication = (Application) iter.next();
//					Query query = session.createQuery("from ApplicationContact ac where ac.application.id = :applicationId");
//					query.setParameter("applicationId", loadedApplication.getId());
//					List contacts = query.getResultList();
//					loadedApplication.setContacts(contacts);
					applications.add(loadedApplication);
			}
			tx1.commit();
			
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
	
	public Map getApplicationCountsByStatus()
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		Map resultsMap = new HashMap();
		try
		{
			tx = session.beginTransaction();
			List list = session.createQuery("SELECT a.applicationStatus, count(a.applicationStatus) FROM Application a GROUP BY a.applicationStatus").getResultList();
			
			for (Iterator iter = list.iterator(); iter.hasNext();)
			{
				Object[] row = (Object[])iter.next();
				resultsMap.put(row[0], row[1]);
			}
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

		return resultsMap;
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
			Query query = session.createQuery("FROM Application a JOIN FETCH a.contacts ac where a.id = :applicationId");
			query.setParameter("applicationId", applicationId);
			
			List list = query.getResultList();
					
			
			if(list!=null && list.size()>0)
			{	
				application = (Application)list.get(0);
			}
			else
			{
				query = session.createQuery("FROM Application a where a.id = :applicationId");
				query.setParameter("applicationId", applicationId);
				
				list = query.getResultList();
				if(list!=null && list.size()>0)
				{	
					application = (Application)list.get(0);
				}
				
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

	public Application updateApplication(Application applicationWithUpdates)
	{
		Application updatedApplication = applicationWithUpdates;
		Session session = sessionFactory.openSession();
		Transaction tx = null;

		try
		{
			tx = session.getTransaction();
			tx.begin();
			String hql = "update Application set applicationDate=?, "
					+ "positionName=?, "
					+ "positionId=?, "
					+ "jobDescription=?, "
					+ "endClient=?, "
					+ "location=?, "
					+ "jobSourceName=?, "
					+ "jobSourceType=?, "
					+ "positionType=?,"
					+ "applicationStatus=? "
					+ "where id=?";
					
			Query query = session.createQuery(hql);
			query.setParameter(0, applicationWithUpdates.getApplicationDate());
			query.setParameter(1, applicationWithUpdates.getPositionName());
			query.setParameter(2, applicationWithUpdates.getPositionId());
			query.setParameter(3, applicationWithUpdates.getJobDescription());
			query.setParameter(4, applicationWithUpdates.getEndClient());
			query.setParameter(5, applicationWithUpdates.getLocation());
			query.setParameter(6, applicationWithUpdates.getJobSourceName());
			query.setParameter(7, applicationWithUpdates.getJobSourceType());
			query.setParameter(8, applicationWithUpdates.getPositionType());
			query.setParameter(9, applicationWithUpdates.getApplicationStatus());
			query.setParameter(10, applicationWithUpdates.getId());
						
			query.executeUpdate();
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
		
		return updatedApplication;
	}
	
	public ApplicationContact updateApplicationContact(ApplicationContact contact)
	{
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		ApplicationContact updatedContact=null;
		
		try
		{
			tx = session.getTransaction();
			tx.begin();
			String hql = "update ApplicationContact set contactName=?, "
					+ "contactDescription=? "
					+ "where id=?";
					
			Query query = session.createQuery(hql);
			query.setParameter(0, contact.getContactName());
			query.setParameter(1, contact.getContactDescription());
			query.setParameter(2, contact.getId());
						
			query.executeUpdate();
			tx.commit();			
		}
		catch(HibernateException he)
		{
			System.out.println(he.toString());
			if(tx!=null){
				tx.rollback();
				he.printStackTrace();
			}
		}
		finally
		{
			session.close();
		}
		
		return getApplicationContact(contact.getId());
	}
	
	public ApplicationContact getApplicationContact(Integer contactId)
	{
		ApplicationContact contact = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			Query query = session.createQuery("from ApplicationContact ac where ac.id = :contactId");
			query.setParameter("contactId", contactId);
			
			List list = query.getResultList();
					
			
			if(list!=null && list.size()>0)
			{	
				contact = (ApplicationContact)list.get(0);
			}
			tx.commit();
		}
		catch(HibernateException he)
		{
			he.printStackTrace();	
			if(tx!=null){
				tx.rollback();
				he.printStackTrace();
			}
		}
		finally
		{
			session.close();
		}
		return contact;
	}
	
	public Integer addApplicationContact(Application application, ApplicationContact contact)
	{
		contact.setApplication(application);
		application.getContacts().add(contact);
		Integer contactId = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			
			contactId = (Integer)session.save(contact);
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
		return contactId;		
	}
	
	public Integer addApplicationAttachment(ApplicationAttachment attachment)
	{
		Integer attachmentId = null;
		
		Integer applicationId = attachment.getApplication().getId();
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			
			attachmentId = (Integer)session.save(attachment);
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
		
		
		return attachmentId;
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
	
	public List getAttachmentTypes()
	{
		List<AttachmentType> attachmentTypes = null;
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		
		try
		{
			tx = session.getTransaction();
			tx.begin();
			String query = "from " + AttachmentType.class.getName();
			attachmentTypes = session.createQuery(query).getResultList();
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
		
		return attachmentTypes;
	}
	
	/***
	 * This deletion finally worked after doing the following:
	 * 1) Setting the Cascade property to DETACH in the parent Application entity for the one-to-many association
	 * 2) Retrieving the collection of attachments from the parent application
	 * 3) Removing the child attachment from the collection - achieved by implementing a robust .equals() & .hashCode() method in ApplicationAttachment entity class.
	 * 4) The deleted the child attachment
	 * 5) Then set the collection back to the parent Application object
	 */
	public boolean deleteAttachment(Integer applicationId, Integer attachmentId)
	{
		boolean attachmentDeleted=false;
		
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			ApplicationAttachment attachment = session.load(ApplicationAttachment.class, attachmentId);
		
			Application application = getApplication(applicationId);
			
			List attachments = application.getAttachments();
			attachments.remove(attachment);
			session.delete(attachment);
			application.setAttachments(attachments);

			tx.commit();
			attachmentDeleted=true;
		}
		catch(HibernateException he)
		{
			if(tx!=null){
				tx.rollback();
				attachmentDeleted=false;
				he.printStackTrace();
			}
		}
		finally
		{
			session.close();
			System.out.println("attachmentDeleted: "+attachmentDeleted);
		}
		
		return attachmentDeleted;
	}
	
	public List getApplicationContacts(Integer applicationId)
	{
		List<ApplicationContact> contacts =null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			contacts = session.createQuery("from ApplicationContact ac where ac.application.id="+applicationId.toString()).getResultList();
			
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
		
		return contacts;
	}
	
	public List getApplicationAttachments(Integer applicationId)
	{
		List<ApplicationAttachment> attachments = null;
		Session session = sessionFactory.openSession();
		Transaction tx = null;
		try
		{
			tx = session.getTransaction();
			tx.begin();
			attachments = session.createQuery("from ApplicationAttachment aa where aa.application.id="+applicationId.toString()).getResultList();
			
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
		
		return attachments;
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

}
