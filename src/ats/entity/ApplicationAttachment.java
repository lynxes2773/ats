package ats.entity;
import java.sql.Blob;

public class ApplicationAttachment {
	private Integer id;
	private Application application;
	private String attachmentFilename;
	private String attachmentType;
	private Blob attachmentContent;
	
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public Application getApplication() {
		return application;
	}
	public void setApplication(Application application) {
		this.application = application;
	}
	public String getAttachmentFilename() {
		return attachmentFilename;
	}
	public void setAttachmentFilename(String attachmentFilename) {
		this.attachmentFilename = attachmentFilename;
	}
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	public Blob getAttachmentContent() {
		return attachmentContent;
	}
	public void setAttachmentContent(Blob attachmentContent) {
		this.attachmentContent = attachmentContent;
	}
	
	
	
}
