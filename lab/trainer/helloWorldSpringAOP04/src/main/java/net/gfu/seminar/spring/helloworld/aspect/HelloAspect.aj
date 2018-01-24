package net.gfu.seminar.spring.helloworld.aspect;

/**
 * Plain AspectJ before advice. Similar aspect code with plain Java:
 * <code>
 * @Aspect
 * public class HelloAspect { }
 *   @Before("execution(* net.gfu.seminar.spring.helloworld.Greeting.*())")
 *  public void trace(JoinPoint thisJoinPoint) {...}
 * </code>
 */
public aspect HelloAspect {

    pointcut accessWelcome()
        : execution(* net.gfu.seminar.spring.helloworld.Greeting.*());

    before() : accessWelcome() {
        System.out.printf("Before advise is running: ‘%s’%n", thisJoinPoint);
    }

    after() : accessWelcome() {
            System.out.printf("After advise is running: ‘%s’%n", thisJoinPoint);
    }
}
