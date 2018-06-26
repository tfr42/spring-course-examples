package net.gfu.seminar.spring.lombok;

import lombok.extern.slf4j.Slf4j;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = ApplicationConfig.class)
@Slf4j
public class GreetingTest {

    @BeforeClass
    public static void setUpBeforeClass() throws Exception {
        log.info("setUpBeforeClass");
    }

    @AfterClass
    public static void tearDownAfterClass() throws Exception {
        log.info("tearDownAfterClass");
    }

    @Autowired
    private Greeting greeting;

    @Autowired
    private Guest guest;

    @Before
    public void setUp() throws Exception {
        log.info("setUp");
    }

    @After
    public void tearDown() throws Exception {
        log.info("tearDown");
    }

    @Test(timeout=1000)
    public void testWelcome() {
        assertNotNull(greeting.welcome());
        log.debug(greeting.welcome());
    }

    @Test
    public void testGuestFirstnameAndLastname() {
        assertEquals("Hans" , guest.getFirstName());
        assertEquals("Dampf" , guest.getLastName());
    }

}
