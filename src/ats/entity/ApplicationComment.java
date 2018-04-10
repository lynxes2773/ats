package ats.entity;
import java.sql.Date;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

@Entity
@Table(name="application_comments")
public class ApplicationComment {
	private Integer id;
	private Application application;
	private String commentText;
	private Date commentDate;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)	
	@Column(name = "comment_id", updatable = false, nullable = false)
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

	@Column(name = "comment_text", updatable = true, nullable = false)
	public String getCommentText() {
		return commentText;
	}
	public void setCommentText(String commentText) {
		this.commentText = commentText;
	}
	
	@Column(name = "comment_date", updatable = true, nullable = false)
	public Date getCommentDate() {
		return commentDate;
	}
	public void setCommentDate(Date commentDate) {
		this.commentDate = commentDate;
	}

	@Override
	public boolean equals(Object obj)
	{
		boolean result=false;
		if(obj==null)
			result=false;
		if(!(obj instanceof ApplicationComment))
			result=false;
	
		ApplicationComment comment = (ApplicationComment)obj;
		
		if(comment.getId().equals(this.getId()) &&
		   comment.getCommentDate().toString().equals(this.getCommentDate().toString())) 
		{
			result = true;
		}
		
		return result;
	}
	
	@Override
	public int hashCode()
	{
		int hash = 3;
		hash = 7 * hash + this.getId();
		hash = 7 * hash + this.getApplication().getId();
		hash = 7 * hash + this.getApplication().getPositionName().length();
		
		return hash;
	}
	
}
