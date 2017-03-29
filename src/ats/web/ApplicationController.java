package ats.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import java.util.List;
import java.util.Set;
import java.util.ArrayList;
import java.util.HashSet;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import ats.entity.*;
import ats.web.validation.ApplicationValidator;
import example.hibernate.Candidate;

@RestController
@Component("applicationController")
@Scope("request")
public class ApplicationController extends AbstractController {
	
	private ApplicationService applicationService;
	
	@Autowired
	ApplicationValidator validator;

	@Override	
	@RequestMapping("/applications.htm")
	public ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List applications= applicationService.getApplications();
		return new ModelAndView("Applications", "applications", applications);
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
	public ModelAndView addApplicationContact(@ModelAttribute("applicationData") ApplicationData applicationData)
	{
		ModelAndView modelAndView = new ModelAndView("NewApplication", "applicationData", applicationData);
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		modelAndView.addObject("showContactForm", true);
		return modelAndView;
	}
	
	@RequestMapping(value="/saveApplicationContact.htm", method=RequestMethod.POST)
	public ModelAndView saveApplicationContact(@Valid @ModelAttribute("applicationData") ApplicationData applicationData, BindingResult errors)
	{
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("NewApplication", "applicationData", applicationData);
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		return modelAndView;
	}
	


	
	
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

}
