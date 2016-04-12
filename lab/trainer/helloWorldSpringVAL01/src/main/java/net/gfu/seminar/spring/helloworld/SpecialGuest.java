package net.gfu.seminar.spring.helloworld;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import org.springframework.stereotype.Component;

@Component("specialGuest")
public class SpecialGuest extends Guest {

    private static final long serialVersionUID = -1071668796253730327L;

    //	@Pattern(regexp="\b[A-Za-z0-9._%+-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,4}\b")
    @Pattern(regexp="^.+@.+")
    @NotNull
    @Size(min=4,max=20)
    private String mailAddress;

    public SpecialGuest() {
        // TODO Auto-generated constructor stub
    }

    public SpecialGuest(String firstname, String lastname, String emailaddress) {
        super(firstname, lastname);
        this.mailAddress = emailaddress ;
    }

    public String getMailAddress() {
        return mailAddress;
    }

    public void setMailAddress(String mailAddress) {
        this.mailAddress = mailAddress;
    }

    @Override
    public String toString() {
        return "SpecialGuest [getClass()=" + getClass() + ", getFirstName()="
                + getFirstName() + ", getLastName()=" + getLastName()
                + ", getMailAddress()=" + getMailAddress()
                + ", hashCode()=" + hashCode() + "]";
    }

}
