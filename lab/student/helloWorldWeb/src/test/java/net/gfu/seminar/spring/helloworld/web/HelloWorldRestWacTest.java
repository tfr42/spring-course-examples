package net.gfu.seminar.spring.helloworld.web;

import static org.hamcrest.Matchers.containsString;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import net.gfu.seminar.spring.helloworld.config.PersistenceConfig;
import net.gfu.seminar.spring.helloworld.web.config.DispatcherConfig;

import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes={DispatcherConfig.class, PersistenceConfig.class})
@WebAppConfiguration
public class HelloWorldRestWacTest {
	private MockMvc mockMvc;

	@Autowired
	private WebApplicationContext webApplicationContext;

	@Before
	public void setUp() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext)
				.build();
	}

	@Test @Ignore
	public void testHelloWorldGetMessageShouldReturnJson() throws Exception {
		mockMvc.perform(get("/guests/1"))
				.andExpect(status().isOk())
				.andExpect(content().contentType("application/json"))
				.andExpect(content().string(containsString("Rainer")));
	}
}