package net.gfu.seminar.spring.domain;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import org.springframework.roo.addon.javabean.RooJavaBean;
import org.springframework.roo.addon.jpa.activerecord.RooJpaActiveRecord;
import org.springframework.roo.addon.tostring.RooToString;

@RooJavaBean
@RooToString
@RooJpaActiveRecord
public class Guest {

    @NotNull
    @Size(min = 2)
    private String firstname;

    @NotNull
    @Size(min = 2)
    private String lastname;
}
