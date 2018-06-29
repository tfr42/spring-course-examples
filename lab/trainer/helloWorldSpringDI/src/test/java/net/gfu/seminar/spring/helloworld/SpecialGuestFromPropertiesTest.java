package net.gfu.seminar.spring.helloworld;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.expression.Expression;
import org.springframework.expression.ExpressionParser;
import org.springframework.expression.spel.standard.SpelExpressionParser;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(locations = { "classpath:/guest-properties-config.xml" })
public class SpecialGuestFromPropertiesTest {

	@Autowired
	@Qualifier("specialGuest")
	private Guest guest;

	@Autowired
	@Qualifier("specialGuestExternal")
	private Guest externalGuest;

	@Autowired
	@Qualifier("placeholderGuest")
	private Guest placeholderGuest;

	@Autowired
	@Qualifier("birthdayChild")
	private BirthdayChild birthdayChild;
	
	@Value("#{nameProperties.firstName?:'Elvis'}")
	private String anotherName;
	
	@Test
	public void testProperties() {
		assertNotNull(guest);
		assertEquals("Anna", guest.getFirstName());
		assertEquals("Gramm", guest.getLastName());
	}

	@Test
	public void testPropertiesFromExternalFile() {
		assertNotNull(externalGuest);
		assertEquals("Rainer", externalGuest.getFirstName());
		assertEquals("Unsinn", externalGuest.getLastName());
	}

	@Test
	public void testPropertiesFromExternalFileWithPlaceholder() {
		assertNotNull(placeholderGuest);
		assertEquals("Hans", placeholderGuest.getFirstName());
		assertEquals("Dampf", placeholderGuest.getLastName());
	}

	@Test
	public void testPropertyWithSPELValue() throws ParseException {
		assertNotNull(birthdayChild);
		assertEquals("Hans", birthdayChild.getFirstName());
		assertEquals("Dampf", birthdayChild.getLastName());
		assertEquals(new SimpleDateFormat("dd.MM.yyyy").parse("11.11.2011"), birthdayChild.getDayOfBirth());
	}
	
	@Test
	public void testSystemPropertyWithSpELElvisOperator() {
		assertEquals("Rainer", this.anotherName);
	}

	@Test
	public void testSpelParser() {
		ExpressionParser parser = new SpelExpressionParser();
		Expression exp = parser.parseExpression("'Hello, world'.concat('!')");
		String message = (String) exp.getValue();
		assertEquals("Hello, world!", message);
	}
}
