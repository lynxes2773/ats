package ats.tests;

import junit.framework.TestCase;

import org.junit.Test;
import org.springframework.validation.BindingResult;
import org.springframework.web.servlet.ModelAndView;
import ats.web.*;
import example.hibernate.Candidate;
import ats.util.MockObjectsProvider;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.Map;

public class CandidateControllerTest extends TestCase {

	private CandidateService service;
	private CandidateController controller;
	
	protected void setUp() throws Exception
	{
		service = new CandidateService();
		controller = new CandidateController();
		controller.setService(service);
	}
	
	@Test
	public void testHandleRequestView() throws Exception
	{
		try
		{
			HttpServletRequest request = new MockObjectsProvider().getHttpRequestObject();
			HttpServletResponse response = new MockObjectsProvider().getHttpResponseObject();

			assertNotNull(controller);

			ModelAndView modelAndView = controller.handleRequest(request, response);
			assertNotNull(modelAndView);
			assertNotNull(modelAndView.getViewName());
			assertEquals("candidates",modelAndView.getViewName());
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}
	
	@Test
	public void testAddCandidate() throws Exception
	{
		ModelAndView modelAndView = controller.addNewCandidate();
		assertNotNull(modelAndView);
		assertNotNull(modelAndView.getViewName());
		assertEquals("newcandidate",modelAndView.getViewName());
	}
	
	@Test
	public void testAddSubmittedCandidate()
	{
		BindingResult errors= null;
		Candidate candidate = new Candidate();
		candidate.setFirstName("John");
		candidate.setLastName("Doe");
		candidate.setGender(new Character('M'));
		candidate.setEmail("john.doe@hogwarts.com");
		ModelAndView modelAndView = controller.addSubmittedCandidate(candidate, errors);
		assertNotNull(modelAndView);
		assertNotNull(modelAndView.getViewName());
		assertEquals("candidateadded",modelAndView.getViewName());
		
	}
}
