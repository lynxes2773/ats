package ats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "position_type_master")
public class PositionType implements java.io.Serializable{
	private Integer id;
	private String positionTypeDescription;
	
	public PositionType()
	{}
	
	@Id
	@GeneratedValue(strategy = GenerationType.AUTO)
	@Column(name = "position_type_id")
	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}
	
	@Column(name = "position_type_description")
	public String getPositionTypeDescription() {
		return positionTypeDescription;
	}
	
	private void setPositionTypeDescription(String positionTypeDescription) {
		this.positionTypeDescription = positionTypeDescription;
	}

}
