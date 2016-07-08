package net.gfu.seminar.spring.helloworld.aspect;
/**
 * <code>
 * @Aspect
 * public class HelloAspect { }
 *   @Before("execution(* net.gfu.seminar.spring.helloworld.Greeting.welcome())")
 *  public void trace(JoinPoint thisJoinPoint) {...}
 * </code>
 */
public aspect HelloAspect {

    pointcut accessWelcome()
        : execution(* net.gfu.seminar.spring.helloworld.Greeting.*());


    before() : accessWelcome() {
        System.out.printf("Before advise is running: ‘%s’%n", thisJoinPoint);
    }
}
