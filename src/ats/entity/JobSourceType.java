package ats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="job_source_type_master")
public class JobSourceType implements java.io.Serializable{

	private Integer id;
	private String jobSourceTypeDescription;
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "job_source_type_id")
	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "job_source_type_description")
	public String getJobSourceTypeDescription() {
		return jobSourceTypeDescription;
	}
	
	private void setJobSourceTypeDescription(String jobSourceTypeDescription) {
		this.jobSourceTypeDescription = jobSourceTypeDescription;
	}
	
	
}
