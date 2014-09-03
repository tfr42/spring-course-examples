package net.gfu.seminar.spring.helloworld.web;

import java.util.List;

import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.GuestDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/greeting")
@Scope("request")
public class GreetingController {
	
	@Autowired
	private GuestDao dao;
	
	@RequestMapping(method=RequestMethod.GET, produces="text/plain")
	public @ResponseBody String sayHelloWorld() {
		return "Hello, world!";
	}
	
	@RequestMapping(value="/guests", method=RequestMethod.GET, produces="application/json")
	public @ResponseBody List<Guest> showAllGuests() {
		return dao.findAll();
	}
	

}
