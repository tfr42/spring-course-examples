package net.gfu.seminar.spring.helloworld;

import javax.servlet.http.HttpServletRequest;

import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

@Controller
@RequestMapping("/helloworld")
@Scope("request")
public class HelloWorld {
	
	/**
	 * http://localhost:8080/helloWorldSpringREST/rest/helloworld
	 * @return
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String returnPlainText() {
		return "Hello, world!";
	}


	/** 
	 * http://localhost:8080/helloWorldSpringREST/rest/helloworld/HansMapf
	 */
	@RequestMapping(value = "/{name}", method = RequestMethod.GET)
	public @ResponseBody String returnTextResponse(@PathVariable String name) {
		return "Hello, " + name + "!";
	}

	/**
	 * http://localhost:8080/helloWorldSpringREST/rest/helloworld/Rainer/Zufall
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	@RequestMapping(value = "/{firstname}/{lastname}", method = RequestMethod.GET, produces={"text/xml", "application/xml"})
	public @ResponseBody ResponseMessage returnAsXmlResponse(@PathVariable String firstname,
			@PathVariable String lastname) {
		return new ResponseMessage("Hello, " + firstname + " " + lastname + "!");
	}
	
	/**
	 * http://localhost:8080/helloWorldSpringREST/rest/helloworld/guest/Rainer/Zufall
	 * @param firstname
	 * @param lastname
	 * @return
	 */
	@RequestMapping(value = "/guest/{firstname}/{lastname}", method = RequestMethod.GET, produces={"application/json", "application/xml", "text/xml"})
	public @ResponseBody Guest returnAsJsonResponse(@PathVariable String firstname,
			@PathVariable String lastname) {
		if (firstname==null || lastname==null || lastname.isEmpty() || lastname.length()<2 ) throw new IllegalArgumentException("Provide firstname and lastname");
		return new Guest(firstname ,lastname);
	}
	
	/**
	 * Exception resolver for <code>IllegalArgumentException</code>.
	 * @param req 
	 * @param exception
	 */
	@ResponseStatus(value=HttpStatus.BAD_REQUEST, reason="Invalid firstname or lastname") 
	@ExceptionHandler(IllegalArgumentException.class)
	public void illegalInput(HttpServletRequest req, IllegalArgumentException exception) {
		System.err.println("Request: " + req.getRequestURL() + " raised " + exception);
	}
}
