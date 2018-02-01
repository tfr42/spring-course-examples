package net.gfu.seminar.spring.helloworld.utils;

import net.gfu.seminar.spring.helloworld.Guest;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.List;

@XmlRootElement(name = "guests")
@XmlAccessorType(XmlAccessType.FIELD)
public class GuestList {
    @XmlElement(name = "guest")
    private List<Guest> guests = null;

    public GuestList() {
    }

    public GuestList(List<Guest> guests) {
        this.setGuests(guests);
    }

    public List<Guest> getGuests() {
        return this.guests;
    }

    public void setGuests(List<Guest> guests) {
        this.guests = guests;
    }

}
