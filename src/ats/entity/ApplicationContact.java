package ats.entity;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="application_contacts")
public class ApplicationContact{
	private Integer id;
	private Application application;
	private String contactName;
	private String contactDescription;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "contact_id", updatable = false, nullable = false)
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
    @ManyToOne
    @JoinColumn(name="application_id")
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}

	@Column(name = "contact_name", updatable = true, nullable = false)
	public String getContactName() {
		return contactName;
	}
	public void setContactName(String contactName) {
		this.contactName = contactName;
	}
	
	@Column(name = "contact_description", updatable = true, nullable = false)
	public String getContactDescription() {
		return contactDescription;
	}
	public void setContactDescription(String contactDescription) {
		this.contactDescription = contactDescription;
	}
	
	
}
