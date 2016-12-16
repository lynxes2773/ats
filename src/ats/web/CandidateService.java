package ats.web;

import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import example.hibernate.Candidate;
import example.util.HibernateExampleUtil;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;

import ats.data.HibernateDAOProvider;

@Component("atsService")
@Service
@Scope("request")
public class CandidateService implements java.io.Serializable{
	
	private List candidates = new ArrayList();
	HibernateDAOProvider manager = null;
	
	public CandidateService()
	{
		manager = new HibernateDAOProvider();
		candidates = manager.getCandidates();
	}

	public List getCandidates() {
		return candidates;
	}

	public void setCandidates(List candidates) {
		this.candidates = candidates;
	}
	
	public Candidate addCandidate(Candidate candidate)
	{
		manager = new HibernateDAOProvider();
		Integer candidateId = manager.addCandidate(candidate);
		Candidate fetchedCandidate = manager.getCandidate(candidateId);
		
		return fetchedCandidate;
	}
}
