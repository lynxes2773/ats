package ats.entity;

public class PositionType implements java.io.Serializable{
	private Integer id;
	private String positionTypeDescription;
	
	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}
	
	public String getPositionTypeDescription() {
		return positionTypeDescription;
	}
	
	private void setPositionTypeDescription(String positionTypeDescription) {
		this.positionTypeDescription = positionTypeDescription;
	}
}
