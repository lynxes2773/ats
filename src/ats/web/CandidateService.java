package ats.web;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import example.hibernate.Candidate;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ats.data.DAOProviderHibernateImpl;
import ats.data.DAOProvider;
import org.springframework.beans.factory.annotation.Autowired;

@Component("atsService")
@Service
@Scope("request")
public class CandidateService implements java.io.Serializable{
	
	private static int PAGE_LIST_LENGTH = 10;
	private List candidates = new ArrayList();
	DAOProvider manager;
	
	@Autowired
	public void setManager(DAOProvider manager) {
		this.manager = manager;
	}
	
	@Autowired
	public CandidateService(DAOProvider manager)
	{
		this.manager=manager;
		//candidates = manager.getCandidatesByPage(1, PAGE_LIST_LENGTH);
	}
	
	public List getCandidatesByPage(int pageId)
	{
		candidates = manager.getCandidatesByPage(pageId, PAGE_LIST_LENGTH);
		return candidates;
	}

	public List getCandidates() {
		return candidates;
	}

	public void setCandidates(List candidates) {
		this.candidates = candidates;
	}
	
	public Candidate addCandidate(Candidate candidate)
	{
		Integer candidateId = manager.addCandidate(candidate);
		Candidate fetchedCandidate = manager.getCandidate(candidateId);
		
		return fetchedCandidate;
	}


}
