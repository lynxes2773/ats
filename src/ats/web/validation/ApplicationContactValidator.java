package ats.web.validation;

import ats.entity.ApplicationContact;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.ValidationUtils;
import org.springframework.validation.Validator;

@Component
public class ApplicationContactValidator implements Validator {

	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		return ApplicationContact.class.equals(clazz);
	}

	@Override
	public void validate(Object target, Errors errors) {

		try
		{
			 ApplicationContact contact = (ApplicationContact) target;
			 
			 String contactName = contact.getContactName();
			 
			 ValidationUtils.rejectIfEmptyOrWhitespace(errors, "applicationContact.contactName", "required");
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
	}

}
