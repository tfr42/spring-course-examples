package net.gfu.seminar.spring.helloworld.groovy;


import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import spock.lang.Narrative
import spock.lang.Specification
import spock.lang.Title

@Title("Application Specification")
@Narrative("Specification which beans are expected")
@ContextConfiguration(locations = "classpath:/beans.groovy")
class GreetingTest extends Specification {

        @Autowired
        private Greeting greeting

        def "when context is loaded then all expected beans are created"() {
            expect: "the greeting bean is created"
            greeting
        }
}
