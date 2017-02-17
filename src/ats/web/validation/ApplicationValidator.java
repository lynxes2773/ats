package ats.web.validation;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;

import ats.entity.Application;

@Component
public class ApplicationValidator implements Validator{
	
	@Override
    public boolean supports(Class<?> clazz) {
        return Application.class.equals(clazz);
    }
	
	 @Override
	 public void validate(Object target, Errors errors) {
		 
		 Application application = (Application) target;
		 
		 String positionName = application.getPositionName();
		 if(positionName==null || positionName.trim().length()==0)
		 {
			 errors.rejectValue("application.positionName", "required");
		 }
		 
		 String positionId = application.getPositionId();
		 if(positionId!=null && positionId.trim().length()>20)
		 {
			 errors.rejectValue("application.positionId", "application.positionid.size");
		 }
		 
		 String jobDescription = application.getJobDescription();
		 if(jobDescription==null || jobDescription.trim().length()==0)
		 {
			 errors.rejectValue("application.jobDescription", "required");
		 }
		 else if(jobDescription!=null && jobDescription.trim().length()>5000)
		 {
			 errors.rejectValue("application.jobDescription", "application.jobdescription.size");
		 }
		 
		 String endClient = application.getEndClient();
		 if(endClient!=null && endClient.trim().length()>255)
		 {
			 errors.rejectValue("application.endClient", "application.endclientname.size");
		 }
		 
		 String location = application.getLocation();
		 if(location!=null && location.trim().length()>50)
		 {
			 errors.rejectValue("application.location", "application.location.size");
	     }
		 
		 String jobSourceName = application.getJobSourceName();
		 if(jobSourceName==null || jobSourceName.trim().length()==0)
		 {
			 errors.rejectValue("application.jobSourceName", "required");
		 }
		 
		 String jobSourceType = application.getJobSourceType();
		 if(jobSourceType==null || jobSourceType.trim().length()==0 || jobSourceType.equals("NONE"))
		 {
			 errors.rejectValue("application.jobSourceType", "required");
		 }
		 
		 String positionType = application.getPositionType();
		 if(positionType==null || positionType.trim().length()==0)
		 {
			 errors.rejectValue("application.positionType", "required");
		 }
		 
		 String applicationStatus = application.getApplicationStatus();
		 if(applicationStatus==null || applicationStatus.trim().length()==0)
		 {
			 errors.rejectValue("application.applicationStatus", "required");
		 }
	 }


}
