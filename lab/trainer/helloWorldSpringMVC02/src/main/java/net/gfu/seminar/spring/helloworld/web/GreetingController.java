package net.gfu.seminar.spring.helloworld.web;

import javax.validation.Valid;

import net.gfu.seminar.spring.helloworld.GreetingService;
import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/greeting")
@Scope("request")
public class GreetingController {
	private static final Logger LOG = Logger
			.getLogger(GreetingController.class);

	@Autowired
	@Qualifier("greetingService")
	private GreetingService service;

	@RequestMapping(value = "/add", method = RequestMethod.GET)
	public String setupForm(Model model) {
		LOG.debug("Form created");
		AddGuestForm guestForm = new AddGuestForm();
		// you can add defaults here
		model.addAttribute(guestForm);
		return "/guest/add";
	}

	@RequestMapping(value = "/welcome", method = RequestMethod.GET)
	public String welcomeForm(Model model) {
		LOG.debug("Form created");
		model.addAttribute("welcome", service.welcome());
		return "/guest/welcome";
	}

	@RequestMapping(value = "/add", method = RequestMethod.POST)
	public ModelAndView processForm(
			@ModelAttribute @Valid AddGuestForm addGuestForm,
			BindingResult result) {
		LOG.debug("Form processed" + addGuestForm);
		ModelAndView mav = new ModelAndView();
		if (result.hasErrors()) {
			mav.setViewName("/guest/add");
		} else {
			service.addGuest(new GuestImpl(addGuestForm.getFirstname(),
					addGuestForm.getLastname()));
			mav.addObject("welcome", service.welcome());
			mav.setViewName("/guest/welcome");
		}
		return mav;
	}

	@RequestMapping(value = "/to/{name}", method = RequestMethod.GET)
	public @ResponseBody
	String getTextMessage(@PathVariable String name) {
		return "Hello, " + name + "!";
	}

	@RequestMapping(value = "/{firstname}/{lastname}", method = RequestMethod.GET)
	public @ResponseBody
	ResponseMessage getXmlMessage(@PathVariable String firstname,
			@PathVariable String lastname) {
		return new ResponseMessage("Hello, " + firstname + " " + lastname + "!");
	}

	@RequestMapping(value = "/{guestId}", method = RequestMethod.GET, produces="text/xml")
	public @ResponseBody
	Guest findGuestById(@PathVariable String guestId) {
		LOG.debug("findGuestById: " + guestId);
		Long id = Long.parseLong(guestId);
		Guest guest = service.findById(id);
		LOG.debug("Found: " + guest);
		return guest;
	}
}
