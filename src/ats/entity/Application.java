package ats.entity;

import java.util.Set;
import java.util.HashSet;

public class Application {

	private Integer id;
	private java.sql.Date applicationDate;
	private String positionName;
	private String positionId;
	private String jobDescription;
	private String endClient;
	private String location;
	private String jobSourceName;
	private String jobSourceType;
	private String positionType;
	private String applicationStatus;

	private Set attachments = new HashSet();
	private Set contacts = new HashSet();
	private Set comments = new HashSet();

	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	public java.sql.Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(java.sql.Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	public String getEndClient() {
		return endClient;
	}

	public void setEndClient(String endClient) {
		this.endClient = endClient;
	}

	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	public String getJobSourceName() {
		return jobSourceName;
	}

	public void setJobSourceName(String jobSourceName) {
		this.jobSourceName = jobSourceName;
	}

	public String getJobSourceType() {
		return jobSourceType;
	}

	public void setJobSourceType(String jobSourceType) {
		this.jobSourceType = jobSourceType;
	}

	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	public Set getAttachments() {
		return attachments;
	}

	public void setAttachments(Set attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(ApplicationAttachment attachment) {
		attachment.setApplication(this);
		attachments.add(attachment);
	}

	public Set getContacts() {
		return contacts;
	}

	public void setContacts(Set contacts) {
		this.contacts = contacts;
	}

	public void addContact(ApplicationContact contact) {
		contact.setApplication(this);
		contacts.add(contact);
	}

	public Set getComments() {
		return comments;
	}

	public void setComments(Set comments) {
		this.comments = comments;
	}
	
	public void addComment(ApplicationComment comment)
	{
		comment.setApplication(this);
		comments.add(comment);
	}
}
