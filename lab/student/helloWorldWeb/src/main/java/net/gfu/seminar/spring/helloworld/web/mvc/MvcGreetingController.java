package net.gfu.seminar.spring.helloworld.web.mvc;

import net.gfu.seminar.spring.helloworld.GreetingService;
import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.dao.GuestRepository;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@RequestMapping("/greeting")
@Scope("request")
public class MvcGreetingController {
	private static final Logger LOG = Logger
			.getLogger(MvcGreetingController.class);

	@Autowired
	private GreetingService service;
    @Autowired
    private GuestRepository dao;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupForm(Model model) {
		LOG.debug("Form created");
		AddGuestForm guestForm = new AddGuestForm();
		// you can add defaults here
		model.addAttribute(guestForm);
		return "guest/add";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomeForm(Model model) {
		LOG.debug("Form created");
		model.addAttribute("welcome", service.welcome());
		return "guest/welcome";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView processForm(
			@ModelAttribute @Valid AddGuestForm addGuestForm,
			BindingResult result) {
		LOG.debug("Form processed" + addGuestForm);
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("guest/add");
		} else {
            Guest guest = new Guest( addGuestForm.getFirstname(), addGuestForm.getLastname() );
			service.addGuest( guest );
            dao.save( guest );
			mav.addObject("welcome", service.welcome());
			mav.setViewName("guest/welcome");
		}
		return mav;
	}

}
