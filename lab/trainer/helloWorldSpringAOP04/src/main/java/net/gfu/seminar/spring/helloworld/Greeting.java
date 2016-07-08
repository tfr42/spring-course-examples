package net.gfu.seminar.spring.helloworld;

/**
 * Says hello to a guest.
 *
 * @author tf
 *
 */
public class Greeting {

    private Guest guest;

    public Greeting(Guest guest) {
        this.guest = guest;
    }

    public String welcome() {
        return String.format("Welcome %1$s to Spring!", this.getGuest());
    }

    private Guest getGuest() {return this.guest;}
}
