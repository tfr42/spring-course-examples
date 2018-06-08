package net.gfu.seminar.spring.helloworld;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.ConfigurableApplicationContext;
import org.springframework.context.annotation.Scope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

/**
 * RESTful controller using Spring MVC.
 * 
 * @author tf
 *
 */
@RestController
@RequestMapping("/helloworld")
@Scope("request")
@CrossOrigin
public class HelloWorld implements ApplicationContextAware {

	private ApplicationContext applicationContext;

	/**
	 * HTTP GET
	 * http://localhost:48080/helloWorldSpringREST/rest/helloworld
	 * @return a String as text/plain
	 */
	@RequestMapping(method = RequestMethod.GET)
	public @ResponseBody String getMessage() {
		return "Hello, world!";
	}

	/**
	 * HTTP GET
	 * http://localhost:48080/helloWorldSpringREST/rest/helloworld/Hans%20Wurst
	 * @param name a name
	 * @return a String with the given name as text/plain
	 */
	@GetMapping("{name}")
	public @ResponseBody String getTextMessage(@PathVariable String name) {
		return "Hello, " + name + "!";
	}

	/**
	 * HTTP GET
	 * http://localhost:48080/helloWorldSpringREST/rest/helloworld/Rainer/Zufall
	 * @param firstname a firstname
	 * @param lastname a lastname
	 * @return the message as text/xml or application/xml
	 */
	@RequestMapping(value = "{firstname}/{lastname}", method = RequestMethod.GET,
			produces={MediaType.TEXT_XML_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody ResponseEntity<Guest> getMessageAsXmlObject(@PathVariable String firstname,
			@PathVariable String lastname) {
		return new ResponseEntity(new Guest(firstname,lastname), HttpStatus.OK);
	}
	
	/**
	 * HTTP GET
	 * http://localhost:48080/helloWorldSpringREST/rest/helloworld/guest/Rainer/Zufall
	 * @param firstname a firstname
	 * @param lastname a lastname
	 * @return the guest as application/json
	 */
	@RequestMapping(value = "guest/{firstname}/{lastname}", method = RequestMethod.GET,
		  produces={MediaType.APPLICATION_JSON_VALUE})
	public @ResponseBody Guest getGuestAsJsonObject(@PathVariable String firstname,
			@PathVariable String lastname) {
		return new Guest(firstname ,lastname);
	}

	/**
	 * HTTP PUT
	 * http://localhost:48080/helloWorldSpringREST/rest/helloworld/guest
	 * @param guest a new guest as application/json or application/xml
	 * @return the guest as application/json or application/xml
	 */
	@RequestMapping(value = "guest", method = RequestMethod.PUT,
			consumes = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE},
			produces = {MediaType.APPLICATION_JSON_VALUE, MediaType.APPLICATION_XML_VALUE})
	public @ResponseBody ResponseEntity<Guest> setGuestAsJsonObject(@RequestBody Guest guest) {
		return new ResponseEntity<Guest>(guest, HttpStatus.OK);
	}

	/**
	 * setApplicationContext
	 */
	public void setApplicationContext(ApplicationContext applicationContext)
			throws BeansException {
		this.applicationContext = applicationContext;
	}

	/**
	 * Refresh the Context
	 * http://localhost:48080/helloWorldSpringREST/rest/reload
	 */
	@RequestMapping(value = "/reload", method = RequestMethod.HEAD)
	public void refreshContext(){
		((ConfigurableApplicationContext)applicationContext).refresh();
	}

}
