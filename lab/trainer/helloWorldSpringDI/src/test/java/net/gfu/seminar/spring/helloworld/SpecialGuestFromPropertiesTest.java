package net.gfu.seminar.spring.helloworld;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = { "classpath:/guest-properties-config.xml" })
public class SpecialGuestFromPropertiesTest {

	@Autowired
	@Qualifier("specialGuest")
	Guest guest;

	@Autowired
	@Qualifier("specialGuestExternal")
	Guest externalGuest;

	@Autowired
	@Qualifier("placeholderGuest")
	Guest placeholderGuest;

	@Autowired
	@Qualifier("birthdayChild")
	BirthdayChild birthdayChild;
	
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
}
