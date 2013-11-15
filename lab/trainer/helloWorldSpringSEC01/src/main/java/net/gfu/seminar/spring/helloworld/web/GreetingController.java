package net.gfu.seminar.spring.helloworld.web;

import javax.validation.Valid;

import net.gfu.seminar.spring.helloworld.GreetingService;
import net.gfu.seminar.spring.helloworld.GuestImpl;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.annotation.Secured;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/greeting")
public class GreetingController {
	
	private static final Logger LOG = Logger.getLogger(GreetingController.class);
	
	@Autowired
	private GreetingService service;

	@RequestMapping(value="/add", method = RequestMethod.GET)
	public String setupForm(Model model) {
		model.addAttribute(new AddGuestForm());
		return "/guest/add";
	}
	
	@RequestMapping(value="/welcome", method = RequestMethod.GET)
	@Secured({"ROLE_ADMIN", "ROLE_USER"})
	public String welcome(Model model) {
		model.addAttribute("welcome", service.welcome());
		return "/guest/welcome";
	}
	
	@RequestMapping(method = RequestMethod.POST)
	@Secured("ROLE_ADMIN")
	public ModelAndView processForm(@ModelAttribute @Valid AddGuestForm addGuestForm,
				BindingResult result) {
		LOG.info("Authorized user: "+ SecurityContextHolder.getContext().getAuthentication());
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("/guest/add");
		} else {
			service.addGuest(new GuestImpl(addGuestForm.getFirstname(), addGuestForm.getLastname()));
			mav.addObject("welcome", service.welcome());
			mav.setViewName("/guest/welcome");
		}
		return mav;
	}


}

