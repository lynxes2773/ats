package ats.entity;

public class JobSourceType implements java.io.Serializable{

	private Integer id;
	private String jobSourceTypeDescription;
	
	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}
	
	public String getJobSourceTypeDescription() {
		return jobSourceTypeDescription;
	}
	
	private void setJobSourceTypeDescription(String jobSourceTypeDescription) {
		this.jobSourceTypeDescription = jobSourceTypeDescription;
	}
	
	
}
