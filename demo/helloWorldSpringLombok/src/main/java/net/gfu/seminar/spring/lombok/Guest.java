package net.gfu.seminar.spring.lombok;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.util.Assert;

@AllArgsConstructor
@ToString @Getter @Setter @EqualsAndHashCode @Slf4j
public class Guest {
    private final @NonNull String firstName;
    private final @NonNull String lastName;

    public void init() {
        log.debug("init called on " + this.toString());
        Assert.hasText(this.getFirstName(), "Firstname is required");
        Assert.hasText(this.getLastName(), "Lastname is required");
    }
}
