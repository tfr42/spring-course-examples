package net.gfu.seminar.spring.helloworld;

import java.util.Set;

import javax.validation.ConstraintViolation;
import javax.validation.Valid;
import javax.validation.Validator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

@Service
public class ValidatingGreetingService extends Greeting {
	
	@Autowired
	private Validator validator;	
	
	@Override
	public void setGuest(@Valid Guest guest) {
		Set<ConstraintViolation<Guest>> validationResult = validator.validate(guest);
		Assert.isTrue(validationResult.isEmpty(), "Guest is not valid");
		super.setGuest(guest);
	}
	
	@Override
	public void addGuest(Guest guest) {
		this.setGuest(guest);
	}
	

}
