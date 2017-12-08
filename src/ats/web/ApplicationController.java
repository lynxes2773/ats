package ats.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;
import java.util.Map;
import java.util.Set;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ats.entity.*;
import ats.web.validation.ApplicationContactValidator;
import ats.web.validation.ApplicationValidator;
import example.hibernate.Candidate;

@RestController
@Component("applicationController")
@Scope("request")
public class ApplicationController extends AbstractController {
	
	private ApplicationService applicationService;
	
	@Autowired
	ApplicationValidator validator;
	ApplicationContactValidator contactValidator;

	@Override	
	@RequestMapping(value="/applications.htm", method=RequestMethod.GET)
	public ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		
		List applications= applicationService.getApplications();
		Map applicationStatusCounts = applicationService.getApplicationStatusCounts();
		
		final StringWriter sw = new StringWriter();
		final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		
		try
		{
			mapper.writeValue(sw, applicationStatusCounts);
		}
		catch(Exception e)
		{
			e.toString();
		}
		
		ModelAndView modelAndView = new ModelAndView("Applications", "applications", applications);
		modelAndView.addObject("chartData", sw.toString());
		
		return modelAndView;
	}
	
	@RequestMapping(value="/apps.htm", method=RequestMethod.GET)
	public String getApplications() 
	{
		List applications= applicationService.getApplications();
		
		final StringWriter sw = new StringWriter();
		final com.fasterxml.jackson.databind.ObjectMapper mapper = new com.fasterxml.jackson.databind.ObjectMapper();
		
		try
		{
			mapper.writeValue(sw, applications);
		}
		catch(Exception e)
		{
			e.toString();
		}
		
		return sw.toString();
	}
	
	//shows brand new screen with application and sub-forms loaded for first time
	@RequestMapping(value="/addNewApplication.htm", method=RequestMethod.GET)
	public ModelAndView addNewApplication()
	{
		ModelAndView modelAndView = new ModelAndView("NewApplication", "applicationData", new ApplicationData());
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		modelAndView.addObject("showContactForm", false);
		return modelAndView;
	}
	
	//submits the entries to add new application and sub-form data 
	@RequestMapping(value="/addSubmittedApplication.htm", method=RequestMethod.POST)
	public ModelAndView addSubmittedApplication(@ModelAttribute("applicationData") ApplicationData applicationData, BindingResult errors)
	{
		validator.validate(applicationData.getApplication(), errors);
		//returns with errors if form has errors
		ModelAndView modelAndView = null;
		if(errors!=null && errors.hasErrors())
		{
			modelAndView =  new ModelAndView("NewApplication", "applicationData", applicationData); 
			modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
			modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
			modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		}
		else
		{
			Application submittedApplication = applicationData.getApplication();
			ApplicationContact submittedContact = applicationData.getApplicationContact();
			
			if(submittedContact!=null && submittedContact.getContactName().trim().length()>0)
			{
				submittedContact.setApplication(submittedApplication);
				List contacts = new ArrayList();
				contacts.add(submittedContact);
				submittedApplication.setContacts(contacts);
			}
			//returns successfully added application details back to front-end on detail screen 
			Application newlyAddedApplication = applicationService.addApplication(submittedApplication);
			
			applicationData.setApplication(newlyAddedApplication);
			modelAndView =  new ModelAndView("ApplicationDetail", "applicationData", applicationData);
			modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
			modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
			modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
			modelAndView.addObject("applicationEditable", false);
		}
		return modelAndView;
	}
	
	//Shows the screen with the selected Application detail
	@RequestMapping(value="/showApplication.htm", method=RequestMethod.GET)
	public ModelAndView showApplicationDetail(@ModelAttribute("id") Integer id)
	{
		Application application = applicationService.getApplication(id);
		List<ApplicationContact> contacts = application.getContacts();
		ApplicationContact contact = null;
		if(contacts!=null && contacts.size()>0)
		{
			contact = contacts.get(0); 
		}
		ApplicationData applicationData = new ApplicationData();
		applicationData.setApplication(application);
		applicationData.setApplicationContact(contact);
		
		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		return modelAndView;
	}
	
	//User has clicked Edit icon on Application Detail
	@RequestMapping(value="/showApplicationEditable.htm", method=RequestMethod.GET)
	public ModelAndView showApplicationDetailsAsEditable(@ModelAttribute("id") Integer id)
	{
		Application application = applicationService.getApplication(id);
		List<ApplicationContact> contacts = application.getContacts();
		ApplicationContact contact = null;
		if(contacts!=null && contacts.size()>0)
		{
			contact = contacts.get(0); 
		}
		ApplicationData applicationData = new ApplicationData();
		applicationData.setApplication(application);
		applicationData.setApplicationContact(contact);

		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("applicationEditable", true);
		return modelAndView;
	}
	
	//User has submitted changes on Application Detail page
	@RequestMapping(value="/updateApplication.htm", method=RequestMethod.POST)
	public ModelAndView updateApplicationDetails(@ModelAttribute("applicationData") ApplicationData applicationData, BindingResult errors)
	{
		Application submittedApplication = applicationData.getApplication();
		validator.validate(submittedApplication, errors);
		
		ModelAndView modelAndView = null;
		if(errors!=null && errors.hasErrors())
		{
			modelAndView =  new ModelAndView("ApplicationDetail", "applicationData", applicationData);
			modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
			modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
			modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
			modelAndView.addObject("applicationEditable", true);
		}
		else
		{
			Application newlyUpdatedApplication = applicationService.updateApplication(submittedApplication);
			applicationData.setApplication(newlyUpdatedApplication);
			modelAndView =  new ModelAndView("ApplicationDetail", "applicationData", applicationData);
			modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
			modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
			modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
			modelAndView.addObject("applicationEditable", false);
			
		}
		return modelAndView;
	}
	
	//User has clicked Edit link on Contacts card; we need to show the Contact entry form on the card.
	@RequestMapping(value="/editApplicationContact.htm", method=RequestMethod.GET)
	public ModelAndView editApplicationContact(@ModelAttribute("applicationData") ApplicationData applicationData, @ModelAttribute("contactId") Integer contactId, @ModelAttribute("applicationId") Integer applicationId)
	{
		applicationData = getApplicationData(applicationId);
				
		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showContactForm", true);
		return modelAndView;
	}
	
	//User has made changes on Contacts card and tried to save changes; we need to validate the changes and save if no errors.
	@RequestMapping(value="/saveApplicationContact.htm", method=RequestMethod.POST)
	public ModelAndView saveApplicationContact(@Valid @ModelAttribute("applicationData") ApplicationData applicationData, BindingResult errors)
	{
		//contactValidator.validate(applicationData.getApplicationContact(), errors);
		//applicationData = getApplicationData(applicationData.getApplication().getId());
		
		applicationData = applicationService.updateApplicationContact(applicationData.getApplication(), applicationData.getApplicationContact());
		
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		modelAndView.addObject("applicationEditable", false);
		if(errors!=null && errors.hasErrors())
		{
			modelAndView.addObject("showContactForm", true);
		}
		else
		{
			modelAndView.addObject("showContactForm", false);
		}
		
		return modelAndView;
	}
	
	//User has cancelled changes on Contacts card we need to show whole page in read-only mode.
	@RequestMapping(value="/cancelContactEditing.htm", method=RequestMethod.GET)
	public ModelAndView cancelContactEditing(@ModelAttribute("applicationData") ApplicationData applicationData)
	{
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showContactForm", false);
		return modelAndView;
	}	
	
	
	public ApplicationData getApplicationData(Integer applicationId)
	{
		ApplicationData applicationData = new ApplicationData();
		
		Application application = applicationService.getApplication(applicationId);
		List<ApplicationContact> contacts = application.getContacts();
		ApplicationContact contact = null;
		if(contacts!=null && contacts.size()>0)
		{
			contact = contacts.get(0); 
		}
		
		applicationData.setApplication(application);
		applicationData.setApplicationContact(contact);
		
		return applicationData;
	}
	
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

}
