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

@Entity
@Table(name = "dummy_attachments")
public class DummyAttachment {
	private Integer id;
	private DummyApplication application;
	private String attachmentFilename;
	private String attachmentType;
	private Blob attachmentContent;
	
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
    public DummyApplication getApplication() {
		return application;
	}
	public void setApplication(DummyApplication application) {
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
	public Blob getAttachmentContent() {
		return attachmentContent;
	}
	public void setAttachmentContent(Blob attachmentContent) {
		this.attachmentContent = attachmentContent;
	}
	
	
	

}