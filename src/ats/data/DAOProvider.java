package ats.data;

import java.util.List;
import java.util.Map;
import ats.entity.Application;
import ats.entity.ApplicationAttachment;
import ats.entity.ApplicationContact;
import ats.entity.ApplicationData;
import ats.util.Provider;
import example.hibernate.Candidate;

public interface DAOProvider extends Provider{
	
	public List getCandidatesByPage(int pageId, int totalPageCount);
	
	public List getCandidates();
	
	public void setCandidates(List candidates);
	
	public Candidate getCandidate(Integer candidateId);
	
	public Integer addCandidate(Candidate candidate);
	
	public List getApplications();
	
	public void setApplications(List applications);

	public Application getApplication(Integer applicationId);
	
	public ApplicationContact getApplicationContact(Integer contactId);
	
	public Integer addApplication(Application application);
	
	public Integer addApplicationContact(Application application, ApplicationContact contact);
	
	public List getApplicationStatuses();
	
	public List getPositionTypes();
	
	public List getJobSourceTypes();
	
	public List getAttachmentTypes();

	public Application updateApplication(Application application);
	
	public ApplicationContact updateApplicationContact(ApplicationContact contact);

	public Map getApplicationCountsByStatus();
	
	public Integer addApplicationAttachment(ApplicationAttachment attachment);
	
	public void removeApplicationAttachment(Integer attachmentId);
}
