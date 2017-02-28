package ats.web;

import java.io.Serializable;
import org.hibernate.HibernateException;

import org.hibernate.Session;
import org.hibernate.Transaction;

import example.hibernate.Candidate;
import ats.entity.*;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import org.springframework.stereotype.Component;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import ats.data.DAOProviderHibernateImpl;
import ats.data.DAOProvider;
import org.springframework.beans.factory.annotation.Autowired;

@Component("applicationService")
@Service
@Scope("request")
@Transactional
public class ApplicationService implements Serializable {

	private DAOProvider manager;
	private List applications = new ArrayList();
	private List applicationStatusTypes = new ArrayList();
	private List positionTypes = new ArrayList();
	private List jobSourceTypes = new ArrayList();
	
	@Autowired
	public void setManager(DAOProvider manager) {
		this.manager = manager;
	}
	
	public List getApplications() {
		return applications;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public Application addApplication(Application application)
	{
		Integer applicationId = manager.addApplication(application);
		Application fetchedApplication = manager.getApplication(applicationId);
		return fetchedApplication;
	}
	
	@Transactional(propagation=Propagation.REQUIRED, readOnly=true, noRollbackFor=Exception.class)
	public ApplicationContact addApplicationContact(Application application, ApplicationContact contact)
	{
		Integer contactId = manager.addApplicationContact(application, contact);
		ApplicationContact fetchedContact = manager.getApplicationContact(contactId);
		return fetchedContact;
	}

	public void setApplications(List applications) {
		this.applications = applications;
	}

	@Autowired
	public ApplicationService(DAOProvider manager)
	{
		this.manager=manager;
		//applications = manager.getApplications();
		setApplicationStatusTypes(manager.getApplicationStatuses());
		setPositionTypes(manager.getPositionTypes());
		setJobSourceTypes(manager.getJobSourceTypes());
	}
	
	public List getApplicationStatusTypes() {
		return applicationStatusTypes;
	}

	public void setApplicationStatusTypes(List applicationStatusTypes) {
		this.applicationStatusTypes = applicationStatusTypes;
	}

	public List getPositionTypes() {
		return positionTypes;
	}

	public void setPositionTypes(List positionTypes) {
		this.positionTypes = positionTypes;
	}

	public List getJobSourceTypes() {
		return jobSourceTypes;
	}

	public void setJobSourceTypes(List jobSourceTypes) {
		this.jobSourceTypes = jobSourceTypes;
	}
}
