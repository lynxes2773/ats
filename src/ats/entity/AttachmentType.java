package ats.entity;

public class AttachmentType implements java.io.Serializable {
	private Integer id;
	private String attachmentTypeName;
	
	public Integer getId() {
		return id;
	}
	
	private void setId(Integer id) {
		this.id = id;
	}

	public String getAttachmentTypeName() {
		return attachmentTypeName;
	}

	private void setAttachmentTypeName(String attachmentTypeName) {
		this.attachmentTypeName = attachmentTypeName;
	}	
}
