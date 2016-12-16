package ats.tests;

import junit.framework.TestCase;
import java.util.List;
import ats.biz.ApplicationManager;
import example.hibernate.Candidate;

public class ApplicationManagerTest extends TestCase {
	
	private ApplicationManager manager;
	
	protected void setUp() throws Exception
	{
		manager = new ApplicationManager();
	}
	
	public void testGetCandidates()
	{
		List candidates = manager.getCandidates();
		assertNotNull(candidates);
		assertTrue(candidates.size()>0);
	}
	
//	public void testAddCanddiate()
//	{
//		ApplicationManager manager = new ApplicationManager();
//		Candidate candidate = new Candidate();
//		candidate.setFirstName("John");
//		candidate.setLastName("Doe");
//		candidate.setGender(new Character('M'));
//		candidate.setEmail("john.doe@somecompany.com");
//
//		Integer candidateId = manager.addCandidate(candidate);
//		assertNotNull(candidateId);
//	}
	
	public void testGetCandidate()
	{
		ApplicationManager manager = new ApplicationManager();
		Candidate candidate = manager.getCandidate(new Integer(1));
		assertNotNull(candidate);
		assertNotNull(candidate.getCandidateId());
	}

}
