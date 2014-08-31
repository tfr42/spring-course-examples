package net.gfu.seminar.spring.helloworld.web.rest;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import net.gfu.seminar.spring.helloworld.config.ApplicationConfig;
import net.gfu.seminar.spring.helloworld.web.config.MvcConfig;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={ApplicationConfig.class, MvcConfig.class})
@ActiveProfiles("dev")
@WebAppConfiguration
public class RestGreetingControllerTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}
	
	@Test
	public void testHelloWorldGetMessageShouldReturnText() throws Exception {
		mockMvc.perform(get("/greetings"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("text/plain"));
	}
	
	@Test
	public void verifyThatGreetingWithIdReturnsJson() throws Exception {
		mockMvc.perform(get("/greetings/guests/1"))
				.andExpect(status().isOk())
				.andExpect( content().contentType( "application/json" ) );
	}
	
}