package ats.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.ElementCollection;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.GroupSequence;
import javax.validation.constraints.Size;

import org.hibernate.validator.constraints.NotEmpty;
import org.springframework.format.annotation.DateTimeFormat;

import ats.web.validation.Format;
import ats.web.validation.Length;
import ats.web.validation.Required;
import example.hibernate.Candidate;

import java.text.SimpleDateFormat;
import java.util.ArrayList;

@Entity
@Table(name = "APPLICATION")
@GroupSequence({Required.class, Length.class, Format.class, Application.class})
public class Application implements java.io.Serializable {

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
	private List<ApplicationAttachment> attachments = new ArrayList();
	private List<ApplicationContact> contacts = new ArrayList();
	private List<ApplicationComment> comments = new ArrayList();

	public Application()
	{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "application_id", updatable = false, nullable = false)
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "application_date", updatable = true, nullable = false)
	//@DateTimeFormat(pattern="MMM dd, yyyy")
	public java.sql.Date getApplicationDate() {
		return applicationDate;
	}

	public void setApplicationDate(java.sql.Date applicationDate) {
		this.applicationDate = applicationDate;
	}

	@Column(name = "position_name", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=1, max=255, message="{application.positionname.size}", groups=Length.class)
	public String getPositionName() {
		return positionName;
	}

	public void setPositionName(String positionName) {
		this.positionName = positionName;
	}

	@Column(name = "positionId", updatable = true, nullable = true)
	@Size(min=1, max=20, message="{application.positionid.size}", groups=Length.class)
	public String getPositionId() {
		return positionId;
	}

	public void setPositionId(String positionId) {
		this.positionId = positionId;
	}

	@Column(name = "job_description", updatable = true, nullable = true)
	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=1, max=5000, message="{application.jobdescription.size}", groups=Length.class)
	public String getJobDescription() {
		return jobDescription;
	}

	public void setJobDescription(String jobDescription) {
		this.jobDescription = jobDescription;
	}

	@Column(name = "end_client", updatable = true, nullable = true)
	@Size(min=1, max=255, message="{application.endclientname.size}", groups=Length.class)
	public String getEndClient() {
		return endClient;
	}

	public void setEndClient(String endClient) {
		this.endClient = endClient;
	}

	@Column(name = "location", updatable = true, nullable = true)
	@Size(min=1, max=50, message="{application.location.size}", groups=Length.class)
	public String getLocation() {
		return location;
	}

	public void setLocation(String location) {
		this.location = location;
	}

	@Column(name = "job_source_name", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	@Size(min=1, max=50, message="{application.jobsourcename.size}", groups=Length.class)
	public String getJobSourceName() {
		return jobSourceName;
	}

	public void setJobSourceName(String jobSourceName) {
		this.jobSourceName = jobSourceName;
	}

	@Column(name = "job_source_type_description", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	public String getJobSourceType() {
		return jobSourceType;
	}

	public void setJobSourceType(String jobSourceType) {
		this.jobSourceType = jobSourceType;
	}

	@Column(name = "position_type_description", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	public String getPositionType() {
		return positionType;
	}

	public void setPositionType(String positionType) {
		this.positionType = positionType;
	}

	@Column(name = "application_status_description", updatable = true, nullable = false)
	@NotEmpty(message="{required}", groups=Required.class)
	public String getApplicationStatus() {
		return applicationStatus;
	}

	public void setApplicationStatus(String applicationStatus) {
		this.applicationStatus = applicationStatus;
	}

	@OneToMany(targetEntity=ApplicationAttachment.class, orphanRemoval=true, cascade=CascadeType.DETACH, fetch=FetchType.EAGER)
	@JoinColumn(name="application_id")
	public List getAttachments() {
		return attachments;
	}

	public void setAttachments(List attachments) {
		this.attachments = attachments;
	}

	public void addAttachment(ApplicationAttachment attachment) {
		attachment.setApplication(this);
		attachments.add(attachment);
	}

	@OneToMany(targetEntity=ApplicationContact.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="application_id")
	public List getContacts() {
		return contacts;
	}

	public void setContacts(List contacts) {
		this.contacts = contacts;
	}

	public void addContact(ApplicationContact contact) {
		contact.setApplication(this);
		contacts.add(contact);
	}

	@OneToMany(targetEntity=ApplicationComment.class, cascade=CascadeType.ALL, fetch=FetchType.LAZY)
	@JoinColumn(name="application_id")
	public List getComments() {
		return comments;
	}

	public void setComments(List comments) {
		this.comments = comments;
	}
	
	public void addComment(ApplicationComment comment)
	{
		comment.setApplication(this);
		comments.add(comment);
	}
	
	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj==null)
			result=false;
		if(!(obj instanceof Application))
			result=false;

		Application application = (Application)obj;
		
		if(application.getPositionName().equals(this.getPositionName()) &&
		   application.getJobDescription().equals(this.getJobDescription()) &&
		   application.getId().equals(this.getId())) 
		{
			result = true;
		}
		
		
		return result;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 7 * hash + this.getPositionName().length();
		hash = 7 * hash + this.getPositionType().length();
		
		return hash;
	}	
}
