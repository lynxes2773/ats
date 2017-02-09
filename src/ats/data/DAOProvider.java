package ats.data;

import java.util.List;

import ats.entity.Application;
import ats.util.Provider;
import example.hibernate.Candidate;

public interface DAOProvider extends Provider{
	
	public List getCandidates();
	
	public void setCandidates(List candidates);
	
	public Candidate getCandidate(Integer candidateId);
	
	public Integer addCandidate(Candidate candidate);
	
	public List getApplications();
	
	public void setApplications(List applications);

	public Application getApplication(Integer applicationId);
	
	public Integer addApplication(Application application);
	
	public List getApplicationStatuses();
	
	public List getPositionTypes();
	
	public List getJobSourceTypes();
	

}
