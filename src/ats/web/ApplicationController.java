package ats.web;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import example.hibernate.Candidate;
import ats.entity.Application;
import ats.entity.ApplicationStatusType;

import java.util.List;
import java.util.ArrayList;
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

@RestController
@Component("applicationController")
@Scope("request")
public class ApplicationController extends AbstractController {
	
	private ApplicationService applicationService; 

	@Override	
	@RequestMapping("/applications.htm")
	public ModelAndView handleRequestInternal(HttpServletRequest arg0, HttpServletResponse arg1) throws Exception {
		List applications=new ArrayList();
		return new ModelAndView("Applications", "applications", applications);
	}
	
	@RequestMapping(value="/addNewApplication.htm", method=RequestMethod.GET)
	public ModelAndView addNewApplication()
	{
		ModelAndView modelAndView = new ModelAndView("NewApplication", "application", new Application());
		modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
		modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
		modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		return modelAndView;
	}
	
	@RequestMapping(value="/addSubmittedApplication.htm", method=RequestMethod.POST)
	public ModelAndView addSubmittedApplication(@Valid @ModelAttribute("application") Application application, BindingResult errors)
	{
		ModelAndView modelAndView = null;
		if(errors!=null && errors.hasErrors())
		{
			modelAndView =  new ModelAndView("NewApplication", "application", application); 
			modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
			modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
			modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		}
		else
		{	
			Application newlyAddedApplication = applicationService.addApplication(application);
			modelAndView =  new ModelAndView("ApplicationDetail", "application", newlyAddedApplication);
			modelAndView.addObject("applicationStatusTypes", applicationService.getApplicationStatusTypes());
			modelAndView.addObject("positionTypes", applicationService.getPositionTypes());
			modelAndView.addObject("jobSourceTypes", applicationService.getJobSourceTypes());
		}
		return modelAndView;
	}
	
	@Autowired
	public void setApplicationService(ApplicationService applicationService) {
		this.applicationService = applicationService;
	}

}
