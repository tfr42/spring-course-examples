import net.gfu.seminar.spring.helloworld.groovy.Greeting
import net.gfu.seminar.spring.helloworld.groovy.Guest

beans {
    greeting(Greeting) {
        guest = ref('guest')
    }
    guest(Guest) {
        firstName = "Hans"
        lastName = "Dampf"
    }
}