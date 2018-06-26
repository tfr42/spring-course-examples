package net.gfu.seminar.spring.lombok;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@RequiredArgsConstructor(onConstructor = @__(@Autowired)) @ToString
@Getter
@Setter
@EqualsAndHashCode
@Component
public class Greeting {

    private final @NonNull Guest guest;

    public String welcome() {
        return String.format("Welcome %1$s to Spring!", getGuest());
    }
}
