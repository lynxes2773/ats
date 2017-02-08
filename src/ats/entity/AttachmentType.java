package ats.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name="attachments_type_master")
public class AttachmentType implements java.io.Serializable {
	private Integer id;
	private String attachmentTypeName;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "attachment_type_id", updatable = false, nullable = false)
	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}

	@Column(name = "attachment_type_name", updatable = false, nullable = false)
	public String getAttachmentTypeName() {
		return attachmentTypeName;
	}

	private void setAttachmentTypeName(String attachmentTypeName) {
		this.attachmentTypeName = attachmentTypeName;
	}	
}
