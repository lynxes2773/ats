package ats.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import org.springframework.validation.BindingResult;

import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

import ats.data.HibernateDAOProvider;
import example.hibernate.Candidate;

import java.util.List;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@Component("candidateController")
@Scope("request")
public class CandidateController extends AbstractController  {

	private CandidateService service;
	@Override	
	@RequestMapping("/candidates.htm")
	public ModelAndView handleRequestInternal(HttpServletRequest request, HttpServletResponse response) throws Exception {

		List candidates = service.getCandidates();
		return new ModelAndView("candidates", "candidates", candidates);
	}
	
	@RequestMapping(value="/addNewCandidate.htm", method=RequestMethod.GET)
	public ModelAndView addNewCandidate()
	{
		return new ModelAndView("newcandidate", "candidate", new Candidate());
	}
	
	//Validation not happening; empty values going to database
	@RequestMapping(value="/addSubmittedCandidate.htm", method=RequestMethod.POST)
	public ModelAndView addSubmittedCandidate(@Valid @ModelAttribute("candidate") Candidate candidate, BindingResult errors)
	{
		if(errors!=null && errors.hasErrors())
		{
			return new ModelAndView("newcandidate", "candidate", candidate); 
		}
		else
		{	
			Candidate newlyAddedCandidate = service.addCandidate(candidate);
			return new ModelAndView("candidateadded", "candidate", newlyAddedCandidate);
		}
	}
	

	@Autowired
	public void setService(CandidateService service) {
		this.service = service;
	}	
	
}
