package net.gfu.seminar.spring.helloworld;

/**
 * A very special guest.
 */
public class SpecialGuest extends Guest {

    public SpecialGuest() {
        super();
    }

    public SpecialGuest(String firstName, String lastName) {
        super(firstName,lastName);
    }

    @Override
    public String toString() {
        return "SpecialGuest [firstName=" + getFirstName()
                + ", lastName=" + getLastName() + "]";
    }

    @Override
    public String getName() {
        return "SpecialGuest " + super.getName();
    }

}
