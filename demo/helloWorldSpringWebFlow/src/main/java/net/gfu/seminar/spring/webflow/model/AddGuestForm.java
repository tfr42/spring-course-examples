package net.gfu.seminar.spring.webflow.model;

import java.io.Serializable;


public class AddGuestForm implements Serializable {

    private static final long serialVersionUID = -149744409282179291L;

    private String firstname;

    private String lastname;

    public void setLastname(String lastname) {
        this.lastname = lastname;
    }

    public String getLastname() {
        return lastname;
    }

    public void setFirstname(String firstname) {
        this.firstname = firstname;
    }

    public String getFirstname() {
        return firstname;
    }

    @Override
    public String toString() {
        return "AddGuestForm [firstname=" + firstname + ", lastname="
                + lastname + "]";
    }

}
