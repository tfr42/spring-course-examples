package net.gfu.seminar.spring.helloworld.web;

import net.gfu.seminar.spring.helloworld.GreetingService;
import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestImpl;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.validation.Valid;

@Controller
@Scope("request")
public class GreetingController {
	private static final Logger LOG = Logger.getLogger(GreetingController.class);

	@Autowired
	@Qualifier("greetingService")
	private GreetingService service;

	@GetMapping("/add")
	public String setupForm(Model model) {
		LOG.debug("Form created");
		AddGuestForm guestForm = new AddGuestForm();
		// you can add defaults here
		model.addAttribute(guestForm);
		return "/guest/add";
	}

	@GetMapping("/welcome")
	public String welcomeForm(Model model) {
		LOG.debug("Form created");
		model.addAttribute("welcome", service.welcome());
		return "/guest/welcome";
	}

	@PostMapping("/add")
	public ModelAndView processForm(@ModelAttribute @Valid AddGuestForm addGuestForm,
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

	@GetMapping("/to/{name}")
	public @ResponseBody String toTextMessage(@PathVariable String name) {
		return "Hello, " + name + "!";
	}

	@GetMapping("/{firstname}/{lastname}")
	public @ResponseBody
	ResponseEntity<Guest> toGuestAsXml(@PathVariable String firstname,
								 @PathVariable String lastname) {
		return new ResponseEntity<Guest>(new GuestImpl(firstname,lastname), HttpStatus.OK);
	}

	@GetMapping(value="/{guestId}", produces = MediaType.APPLICATION_XML_VALUE)
	public @ResponseBody Guest findGuestById(@PathVariable String guestId) {
		LOG.debug("findGuestById: " + guestId);
		Long id = Long.parseLong(guestId);
		Guest guest = service.findById(id);
		LOG.debug("Found: " + guest);
		if (guest != null) {
			return guest;
		} else {
			throw new GuestNotFoundException(guestId);
		}
	}
}
