package ats.util;

import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import ats.web.*;

public class MockObjectsProvider implements Provider {
	
	public HttpServletRequest getHttpRequestObject()
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		request.setMethod("GET");
		return request;
	}
	
	public HttpServletResponse getHttpResponseObject()
	{
		return new MockHttpServletResponse();
	}
	
}
