package net.gfu.seminar.spring.helloworld;

import static org.hamcrest.Matchers.containsString;
import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.forwardedUrl;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.validation.BeanPropertyBindingResult;
import org.springframework.validation.BindingResult;
import org.springframework.web.context.WebApplicationContext;
import org.springframework.web.servlet.ModelAndView;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({ "file:src/main/webapp/WEB-INF/webContext.xml",
		"file:src/main/webapp/WEB-INF/controller-servlet.xml" })
@WebAppConfiguration
public class GreetingControllerTest {

	private MockMvc mockMvc;

	@Autowired
	private GreetingController controller;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test
	public void testProcessFormViaApi() {
		AddGuestForm form = new AddGuestForm();
		form.setFirstname("Hans");
		form.setLastname("Dampf");

		BindingResult result = new BeanPropertyBindingResult(form,
				"addGuestForm");
		ModelAndView modelAndView = controller.processForm(form, result);
		assertEquals("/guest/welcome", modelAndView.getViewName());
		assertNotNull(modelAndView.getModel().get("welcome"));
	}

	@Test
	public void testProcessFormViaMock() throws Exception {
		mockMvc.perform(post("/greeting/add"))
				.andExpect(status().isOk())
				.andExpect(view().name("/guest/welcome"))
				.andExpect(forwardedUrl("/guest/welcome.jsp"))
				.andExpect(
						model().attribute(
								"welcome",
								containsString("Welcome [GuestImpl [id=1, firstName=Anna, lastName=Gramm]")));
	}
}
