package ats.entity;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component("applicationData")
public class ApplicationData implements java.io.Serializable {
	
	@Valid
	private Application application;
	private ApplicationContact applicationContact;
	
	public ApplicationData()
	{
		application = new Application();
		applicationContact = new ApplicationContact();
	}
	
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public ApplicationContact getApplicationContact() {
		return applicationContact;
	}
	public void setApplicationContact(ApplicationContact applicationContact) {
		this.applicationContact = applicationContact;
	}

}
