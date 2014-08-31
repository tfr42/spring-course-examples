package net.gfu.seminar.spring.helloworld.web.rest;

import javax.inject.Inject;

import net.gfu.seminar.spring.helloworld.Guest;
import net.gfu.seminar.spring.helloworld.dao.GuestRepository;

import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

@RestController
@RequestMapping("/greetings")
public class RestGreetingController {

	@Inject
	private GuestRepository dao;

	@RequestMapping(method = RequestMethod.GET, produces = "text/plain")
	public @ResponseBody
	String welcome() {
		return "Hello, world!";
	}

	@RequestMapping(value = "/guests/{id}", method = RequestMethod.GET, produces = "application/json")
	public @ResponseBody
	Guest findGuest(@PathVariable Long id) {
		return dao.findById(id);
	}

	@RequestMapping(value="/guests", method = RequestMethod.GET, produces="application/json")
	public @ResponseBody
    List<Guest> findAllGuests() {
        List<Guest> list = new ArrayList<Guest>();
        fill(list, dao.findAll().iterator());
        return list;
	}

    @RequestMapping(value="/guests", method = RequestMethod.PUT, consumes = "application/json", produces="application/json")
    public @ResponseBody
    Guest addGuest(@RequestBody Guest guest) {
        return dao.save(guest);
    }

    private static void fill(Collection collection, Iterator iterator) {
        while (iterator.hasNext()) {
            collection.add(iterator.next());
        }
    }

}
