package ats.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component("applicationData")
public class ApplicationData implements java.io.Serializable {
	
	@Valid
	private Application application;
	private DummyApplication dummyApplication;
	private ApplicationContact applicationContact;
	private DummyContact dummyContact;
	
	public ApplicationData()
	{
		application = new Application();
		dummyApplication = new DummyApplication();
		applicationContact = new ApplicationContact();
		dummyContact = new DummyContact();
	}
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public DummyApplication getDummyApplication() {
		return dummyApplication;
	}
	public void setDummyApplication(DummyApplication dummyApplication) {
		this.dummyApplication = dummyApplication;
	}
	public ApplicationContact getApplicationContact() {
		return applicationContact;
	}
	public void setApplicationContact(ApplicationContact applicationContact) {
		this.applicationContact = applicationContact;
	}
	public DummyContact getDummyContact() {
		return dummyContact;
	}
	public void setDummyContact(DummyContact dummyContact) {
		this.dummyContact = dummyContact;
	}

}
