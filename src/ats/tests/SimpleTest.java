package ats.tests;

import junit.framework.TestCase;

public class SimpleTest extends TestCase{
	
	String message = "Hello World";
	
	public void testSimpleAssertion()
	{
		assertEquals("Hello World", message);
	}

}
