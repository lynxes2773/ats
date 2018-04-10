package ats.entity;
import java.sql.Blob;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrimaryKeyJoinColumn;
import javax.persistence.Table;

import org.springframework.stereotype.Component;

@Entity
@Table(name = "application_attachments")
@Component("applicationAttachment")
public class ApplicationAttachment {
	private Integer id;
	private Application application;
	private String attachmentFilename;
	private String attachmentType;
	private byte[] attachmentContent;
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "attachment_id", updatable = false, nullable = false)
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
	
	@Column(name = "attachment_filename", updatable = true, nullable = false)
	public String getAttachmentFilename() {
		return attachmentFilename;
	}
	public void setAttachmentFilename(String attachmentFilename) {
		this.attachmentFilename = attachmentFilename;
	}
	
	@Column(name = "attachment_type_name", updatable = true, nullable = false)
	public String getAttachmentType() {
		return attachmentType;
	}
	public void setAttachmentType(String attachmentType) {
		this.attachmentType = attachmentType;
	}
	
	@Column(name = "attachment_content", updatable = true, nullable = false)
	public byte[] getAttachmentContent() {
		return attachmentContent;
	}
	public void setAttachmentContent(byte[] attachmentContent) {
		this.attachmentContent = attachmentContent;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		
		if(obj==null)
			result=false;
		if(!(obj instanceof ApplicationAttachment))
			result=false;
	
		ApplicationAttachment attachment = (ApplicationAttachment)obj;
		
		if(attachment.getAttachmentFilename().equals(this.getAttachmentFilename()) &&
		   attachment.getAttachmentType().equals(this.getAttachmentType()) &&
		   attachment.getId().equals(this.getId())) 
		{
			result = true;
		}
		return result;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 7 * hash + this.getAttachmentFilename().length();
		hash = 7 * hash + this.getAttachmentType().length();
		
		return hash;
	}
	
}
