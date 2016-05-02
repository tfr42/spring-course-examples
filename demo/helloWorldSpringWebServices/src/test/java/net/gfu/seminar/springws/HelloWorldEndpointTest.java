package net.gfu.seminar.springws;

import static org.springframework.ws.test.server.RequestCreators.withPayload;
import static org.springframework.ws.test.server.ResponseMatchers.payload;

import javax.xml.transform.Source;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.test.server.MockWebServiceClient;
import org.springframework.xml.transform.StringSource;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/test-ws.xml")
public class HelloWorldEndpointTest {

	@Autowired
	private ApplicationContext applicationContext;

	private MockWebServiceClient mockClient;

	@Before
	public void createClient() {
		mockClient = MockWebServiceClient.createClient(applicationContext);
	}

	@Test
	public void verifyThatHelloWorldEndpointReturnsValidResponse() throws Exception {
		Source requestPayload = new StringSource(
				"<typ:helloRequest xmlns:typ='http://seminar.gfu.net/springws/helloworld/types'>"
						+ "<firstname>Hans</firstname>"
						+ "  <lastname>Dampf</lastname>"
						+ "</typ:helloRequest>");
		Source responsePayload = new StringSource(
				"<ns3:helloResponse xmlns:ns3='http://seminar.gfu.net/springws/helloworld/types'>"
						+ "<return>Hello, Hans Dampf!</return>"
						+ "</ns3:helloResponse>");

		mockClient.sendRequest(withPayload(requestPayload)).andExpect(
				payload(responsePayload));
	}
}
