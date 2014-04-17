package net.gfu.seminar.spring.helloworld;

import java.io.Serializable;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

@ManagedBean
@RequestScoped
public class WelcomeForm implements Serializable {
	private static final long serialVersionUID = -1110440082884123464L;
	private String message;
	private GreetingService service;
	

	public void setMessage(String message) {
		this.message = message;
	}

	public String getMessage() {
		if (message==null) message = service.welcome();
		return message;
	}

	public void setService(GreetingService service) {
		this.service = service;
	}

	public GreetingService getService() {
		return service;
	}
}
