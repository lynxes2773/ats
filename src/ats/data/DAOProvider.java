package ats.data;

import java.util.List;

import ats.util.Provider;
import example.hibernate.Candidate;

public interface DAOProvider extends Provider{
	
	public List getCandidates();
	
	public void setCandidates(List candidates);
	
	public Candidate getCandidate(Integer candidateId);
	
	public Integer addCandidate(Candidate candidate);

}
