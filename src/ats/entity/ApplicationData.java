package ats.entity;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;

import org.springframework.stereotype.Component;

@Component("applicationData")
public class ApplicationData implements java.io.Serializable {
	
	@Valid
	private Application application;
	private ApplicationContact applicationContact;
	private ApplicationAttachment newAttachment;
	private List<ApplicationAttachment> attachments;
	

	public ApplicationData()
	{
		application = new Application();
		applicationContact = new ApplicationContact();
		attachments = new ArrayList();
		newAttachment = new ApplicationAttachment();
	}
	
	public Application getApplication() {
		return application;
	}
	public List<ApplicationAttachment> getAttachments() {
		return attachments;
	}

	public void setAttachments(List<ApplicationAttachment> attachments) {
		this.attachments = attachments;
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

	public ApplicationAttachment getNewAttachment() {
		return newAttachment;
	}

	public void setNewAttachment(ApplicationAttachment newAttachment) {
		this.newAttachment = newAttachment;
	}
	
}
