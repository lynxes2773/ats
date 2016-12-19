package ats.tests;

import junit.framework.TestCase;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

import ats.data.DAOProviderHibernateImpl;
import ats.data.HibernateSessionProvider;
import example.hibernate.Candidate;

public class HibernateDAOProviderTest extends TestCase{
	
	private DAOProviderHibernateImpl manager;
	private static SessionFactory sessionFactory;
	
	protected void setUp() throws Exception
	{
        manager = new DAOProviderHibernateImpl();
	}
	
	public void testGetCandidates()
	{
		List candidates = manager.getCandidates();
		assertNotNull(candidates);
		assertTrue(candidates.size()>0);
	}
	
	public void testAddCanddiate()
	{
		DAOProviderHibernateImpl manager = new DAOProviderHibernateImpl();
		Candidate candidate = new Candidate();
		candidate.setFirstName("John");
		candidate.setLastName("Doe");
		candidate.setGender(new Character('M'));
		candidate.setEmail("john.doe@somecompany.com");

		Integer candidateId = manager.addCandidate(candidate);
		assertNotNull(candidateId);
	}
	
	public void testGetCandidate()
	{
		DAOProviderHibernateImpl manager = new DAOProviderHibernateImpl();
		Candidate candidate = manager.getCandidate(new Integer(1));
		assertNotNull(candidate);
		assertNotNull(candidate.getCandidateId());
	}

}


