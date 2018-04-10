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
import java.sql.Blob;
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
import org.springframework.web.multipart.commons.CommonsMultipartFile;
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
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", false);
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
			applicationData = getApplicationData(applicationData.getApplication().getId());
			
			modelAndView =  new ModelAndView("ApplicationDetail", "applicationData", applicationData);
			modelAndView.addObject("applicationEditable", false);
		}
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", false);
		return modelAndView;
	}
	
	//Shows the screen with the selected Application detail
	@RequestMapping(value="/showApplication.htm", method=RequestMethod.GET)
	public ModelAndView showApplicationDetail(@ModelAttribute("id") Integer id)
	{
		System.out.println("#### Application ID: " +id);
		ApplicationData applicationData = getApplicationData(id);
		
		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", false);
		modelAndView.addObject("applicationEditable", false);
		return modelAndView;
	}
	
	//User has clicked Edit icon on Application Detail
	@RequestMapping(value="/showApplicationEditable.htm", method=RequestMethod.GET)
	public ModelAndView showApplicationDetailsAsEditable(@ModelAttribute("id") Integer id)
	{
		ApplicationData applicationData = getApplicationData(id);

		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", false);
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
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("showNewAttachmentForm", false);

		if(errors!=null && errors.hasErrors())
		{
			modelAndView =  new ModelAndView("ApplicationDetail", "applicationData", applicationData);
			modelAndView.addObject("applicationEditable", true);
		}
		else
		{
			Application newlyUpdatedApplication = applicationService.updateApplication(submittedApplication);
			
			Integer applicationId = newlyUpdatedApplication.getId();
			applicationData = getApplicationData(applicationId);
			applicationData.setApplication(newlyUpdatedApplication);
			modelAndView =  new ModelAndView("ApplicationDetail", "applicationData", applicationData);
			modelAndView.addObject("applicationEditable", false);
			
		}
		return modelAndView;
	}
	
	//User has clicked Edit link on Contacts card; we need to show the Contact entry form on the card.
	@RequestMapping(value="/editApplicationContact.htm", method=RequestMethod.GET)
	public ModelAndView editApplicationContact(@RequestParam("id") Integer applicationId)
	{
	 
		ApplicationData applicationData = getApplicationData(applicationId);
		
		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showContactForm", true);
		modelAndView.addObject("showNewAttachmentForm", false);
		
		return modelAndView;
	}
	
	//User has made changes on Contacts card and tried to save changes; we need to validate the changes and save if no errors.
	@RequestMapping(value="/saveApplicationContact.htm", method=RequestMethod.POST)
	public ModelAndView saveApplicationContact(@Valid @ModelAttribute("applicationData") ApplicationData applicationData, BindingResult errors)
	{
		//contactValidator.validate(applicationData.getApplicationContact(), errors);

		ApplicationContact contact = applicationData.getApplicationContact();
		applicationService.updateApplicationContact(contact);
		
		applicationData = getApplicationData(applicationData.getApplication().getId());
		
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showNewAttachmentForm", false);

		boolean showContactForm = false;
		if(errors!=null && errors.hasErrors())
		{
			String errorStr = errors.toString();
			if(errorStr.contains("contactName"))
			{	
				showContactForm = true;
			}
		}
		modelAndView.addObject("showContactForm", showContactForm);
		
		return modelAndView;
	}
	
	//User has cancelled changes on Contacts card we need to show whole page in read-only mode.
	@RequestMapping(value="/cancelContactEditing.htm", method=RequestMethod.GET)
	public ModelAndView cancelContactEditing(@ModelAttribute("applicationData") ApplicationData applicationData)
	{
		applicationData = getApplicationData(applicationData.getApplication().getId());
		
		ModelAndView modelAndView = null;
		modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", false);
		return modelAndView;
	}	
	
	//User has clicked Add link on Attachments card; we need to show the attachment upload form on the card.
	@RequestMapping(value="/addNewAttachment.htm", method=RequestMethod.GET)
	public ModelAndView addAttachment(@RequestParam("id") Integer applicationId)
	{
		ApplicationData applicationData = getApplicationData(applicationId);
		
		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", true);
		return modelAndView;
	}
	
	@RequestMapping(value = "/saveNewAttachment.htm", method = RequestMethod.POST)
	public ModelAndView uploadAttachment(@ModelAttribute("applicationData") ApplicationData applicationData,  @RequestParam("attachmentContent") CommonsMultipartFile attachmentContent)
	{
		ApplicationAttachment attachment = applicationData.getNewAttachment();
		attachment.setAttachmentContent(attachmentContent.getBytes());
		
		Integer applicationId = applicationData.getApplication().getId();

		Application application = applicationData.getApplication();
		List<ApplicationAttachment> attachments = application.getAttachments();
		if(attachments == null)
		{
			attachments = new ArrayList();
		}
		attachments.add(attachment);
		application.setAttachments(attachments);
		
		attachment.setApplication(application);
		applicationData = applicationService.addApplicationAttachment(attachment);
		applicationData = getApplicationData(applicationId);
		
		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", false);
		return modelAndView;
	}
	
	@RequestMapping(value = "/deleteAttachment.htm", method = RequestMethod.GET)
	public ModelAndView deleteAttachment(@RequestParam("id") Integer attachmentId, @RequestParam("applicationId") Integer applicationId)
	{
		applicationService.deleteAttachment(applicationId, attachmentId);

		ApplicationData applicationData = getApplicationData(applicationId);
		
		ModelAndView modelAndView = new ModelAndView("ApplicationDetail", "applicationData", applicationData);
		modelAndView = setMasterData(modelAndView);
		modelAndView.addObject("applicationEditable", false);
		modelAndView.addObject("showContactForm", false);
		modelAndView.addObject("showNewAttachmentForm", false);
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
		
		List<ApplicationAttachment> attachments = applicationService.getAttachments(applicationId); 
		application.setAttachments(attachments);
		applicationData.setApplication(application);
		applicationData.setAttachments(attachments);
		applicationData.setApplicationContact(contact);
		return applicationData;
	}
	

	public ModelAndView setMasterData(ModelAndView modelAndView)
	{
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		modelAndView.addObject("attachmentTypes", applicationService.getAttachmentTypes());
		
		return modelAndView;
	}
	
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

}
