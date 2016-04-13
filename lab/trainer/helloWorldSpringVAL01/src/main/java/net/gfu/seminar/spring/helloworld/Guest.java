package net.gfu.seminar.spring.helloworld;

import org.springframework.stereotype.Component;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Scanner;

@Component("guest")
public class Guest implements Serializable {
    private static final long serialVersionUID = -4700804809827556933L;
    @NotNull
    private String firstName;

    @Size(min=4,max=20)
    private String lastName;

    private Long id;

    public Guest() {
        this("", "");
    }

    public Guest(String firstName, String lastName) {
        super();
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public Guest(Long id, String firstName, String lastName) {
        this(firstName, lastName);
        this.id = id;
    }

    public String getName() {
        return this.firstName + " " + this.lastName;
    }

    public void setName(String name) {
        Scanner scanner = new Scanner(name);
        this.firstName = scanner.next();
        this.lastName = scanner.next();
        scanner.close();
    }

    @Override
    public String toString() {
        return "Guest [firstName=" + firstName + ", lastName=" + lastName
                + "]";
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

}
