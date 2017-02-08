package ats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "application_status_master")
public class ApplicationStatusType implements java.io.Serializable {

	private Integer id;
	private String applicationStatusTypeName;

	public ApplicationStatusType()
	{}

	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "application_status_id")
	public Integer getId() {
		return id;
	}

	public void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "application_status_description")
	public String getApplicationStatusTypeName() {
		return applicationStatusTypeName;
	}

	public void setApplicationStatusTypeName(String applicationStatusTypeName) {
		this.applicationStatusTypeName = applicationStatusTypeName;
	}
}
