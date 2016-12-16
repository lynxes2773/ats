package ats.tests;

import junit.framework.TestCase;
import example.hibernate.Candidate;
import java.sql.Date;

public class CandidateTest extends TestCase {

	private Candidate candidate;
	
	protected void setUp()
	{
		candidate = new Candidate();
	}
	
	public void testSetAndGetCandidateId()
	{
		Integer testCandidateId = new Integer(1);
		assertNull(candidate.getCandidateId());
		candidate.setCandidateId(testCandidateId);
		assertEquals(testCandidateId, candidate.getCandidateId());
	}
	
	public void testSetAndGetFirstName()
	{
		String testFirstName = "aFirstName";
		assertNull(candidate.getFirstName());
		candidate.setFirstName(testFirstName);
		assertEquals(testFirstName, candidate.getFirstName());
	}
	
	public void testSetAndGetLastName()
	{
		String testLastName = "aLastName";
		assertNull(candidate.getLastName());
		candidate.setLastName(testLastName);
		assertEquals(testLastName, candidate.getLastName());
	}
	
	public void testSetAndGetDob()
	{
		Date testDob = new Date(System.currentTimeMillis());
		assertNull(candidate.getDob());
		candidate.setDob(testDob);
		assertEquals(testDob, candidate.getDob());
	}
	
	public void testSetAndGetGender()
	{
		Character testGender = new Character('M');
		assertNull(candidate.getGender());
		candidate.setGender(testGender);
		assertEquals(testGender, candidate.getGender());
	}
	
	public void testSetAndGetEmail()
	{
		String testEmail = "firstname.lastname@somecompany.com";
		assertNull(candidate.getEmail());
		candidate.setEmail(testEmail);
		assertEquals(testEmail, candidate.getEmail());
	}
	
	public void testSetAndGetPhone()
	{
		String testPhone = "1111111111";
		assertNull(candidate.getPhone());
		candidate.setPhone(testPhone);
		assertEquals(testPhone, candidate.getPhone());
	}	
	
	public void testSetAndGetIntlDialingCode()
	{
		String testIntlDialingCode = "001";
		assertNull(candidate.getIntlDialingCode());
		candidate.setIntlDialingCode(testIntlDialingCode);
		assertEquals(testIntlDialingCode, candidate.getIntlDialingCode());
	}
	
	public void testSetAndGetStreetAddress1()
	{
		String testStreetAddress1 = "99 SomeDrive";
		assertNull(candidate.getStreetAddress1());
		candidate.setStreetAddress1(testStreetAddress1);
		assertEquals(testStreetAddress1, candidate.getStreetAddress1());
	}

	public void testSetAndGetStreetAddress2()
	{
		String testStreetAddress2 = "Flr 1";
		assertNull(candidate.getStreetAddress2());		
		candidate.setStreetAddress2(testStreetAddress2);
		assertEquals(testStreetAddress2, candidate.getStreetAddress2());
	}	
	
	public void testSetAndGetCity()
	{
		String testCity = "New York City";
		assertNull(candidate.getCity());
		candidate.setCity(testCity);
		assertEquals(testCity, candidate.getCity());
	}
	
	public void testSetAndGetState()
	{
		String testState = "New York";
		assertNull(candidate.getState());
		candidate.setState(testState);
		assertEquals(testState, candidate.getState());
	}
	
	public void testSetAndGetZipCode() 
	{
		String testZipCode = "99999";
		assertNull(candidate.getZipCode());
		candidate.setZipCode(testZipCode);
		assertEquals(testZipCode, candidate.getZipCode());
	}
	
	public void testSetAndGetCountry()
	{
		String testCountry = "United States";
		assertNull(candidate.getCountry());
		candidate.setCountry(testCountry);
		assertEquals(testCountry, candidate.getCountry());
	}
	
	public void testSetAndGetStatus()
	{
		Character testStatus = new Character('A');
		assertNull(candidate.getStatus());
		candidate.setStatus(testStatus);
		assertEquals(testStatus, candidate.getStatus());
	}
	
	public void testSetAndGetSummary()
	{
		String testSummary = "Great candidate";
		assertNull(candidate.getSummary());
		candidate.setSummary(testSummary);
		assertEquals(testSummary, candidate.getSummary());
	}
	
}
