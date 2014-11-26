package net.gfu.seminar.springws;

import net.gfu.helloworld.types.HelloRequest;
import net.gfu.helloworld.types.HelloResponse;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.ws.client.core.WebServiceTemplate;

import static org.hamcrest.CoreMatchers.allOf;
import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.Assert.assertThat;

/**
 * Created by tf on 26.11.14.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/wsTemplate.xml")
public class HelloWorldWebServiceIntegrationTest {

    @Autowired
    private WebServiceTemplate webServiceTemplate;

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
    public void testSimpleSendAndReceive() {
        HelloRequest requestPayload = new HelloRequest();
        requestPayload.setFirstname( "Hans" );
        requestPayload.setLastname( "Dampf" );
        HelloResponse receivePayload = (HelloResponse) webServiceTemplate
              .marshalSendAndReceive( requestPayload );
        assertThat( receivePayload.getReturn(),
                    allOf( containsString( "Hans" ), containsString( "Dampf" ) ) );
    }
}
