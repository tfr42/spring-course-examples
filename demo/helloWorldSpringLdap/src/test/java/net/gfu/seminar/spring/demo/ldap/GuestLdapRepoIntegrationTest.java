package net.gfu.seminar.spring.demo.ldap;
import static org.junit.Assert.assertEquals;

import java.util.List;

import net.gfu.seminar.spring.demo.ldap.GuestRepo;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration("classpath:/applicationContext.xml")
public class GuestLdapRepoIntegrationTest {

    @Autowired
    private GuestRepo guestRepo;

    @Test
    public void testGetAllGuestNames() {
        List<String> result = guestRepo.getAllGuestNames();
        assertEquals(2, result.size());
        String first = (String) result.get(1);
        assertEquals("Anna Gramm", first);
    }
}