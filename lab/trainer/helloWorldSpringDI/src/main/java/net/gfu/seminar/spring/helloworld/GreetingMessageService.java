package net.gfu.seminar.spring.helloworld;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

/**
 * Created by tf on 26.08.17.
 */
@Transactional
@Service
public class GreetingMessageService extends Greeting {

    private GuestDao dao;

    public GreetingMessageService(GuestDao dao) {
        this.dao = dao;
    }

    @Override
    public String welcome() {
        List<Guest> all = this.dao.findAll();
        String guestNames = all.toString();
        return String.format("Welcome %1$s to Spring!", guestNames);
    }

    public void addGuest(Guest guest) {
        this.dao.create(guest);
    }

    public Guest findById(Long id) {
        return dao.findById(id);
    }

    public List<Guest> findAll() {
        return dao.findAll();
    }
}
