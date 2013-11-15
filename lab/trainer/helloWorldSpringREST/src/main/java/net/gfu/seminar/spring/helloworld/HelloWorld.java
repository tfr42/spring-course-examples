package net.gfu.seminar.spring.helloworld;

import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
@RequestMapping("/helloworld")
@Scope("request")
public class HelloWorld {
	
	/**
	 * http://localhost:8080/helloWorldSpringREST/rest/helloworld
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getMessage() {
		return "Hello, world!";
	}


	/** 
	 * http://localhost:8080/helloWorldSpringREST/rest/helloworld/HansMapf
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody String getTextMessage(@PathVariable String name) {
		return "Hello, " + name + "!";
	}

	/**
	 *  http://localhost:8080/helloWorldSpringREST/rest/helloworld/Rainer/Zufall
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	@RequestMapping(value = "/{firstname}/{lastname}", method = RequestMethod.GET, produces={"text/xml"})
	public @ResponseBody ResponseMessage getXmlMessage(@PathVariable String firstname,
			@PathVariable String lastname) {
		return new ResponseMessage("Hello, " + firstname + " " + lastname + "!");
	}
	
	/**
	 *  http://localhost:8080/helloWorldSpringREST/rest/helloworld/guest/Rainer/Zufall
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	@RequestMapping(value = "/guest/{firstname}/{lastname}", method = RequestMethod.GET, produces={"application/json"})
	public @ResponseBody Guest getJsonMessage(@PathVariable String firstname,
			@PathVariable String lastname) {
		return new Guest(firstname ,lastname);
	}
}
