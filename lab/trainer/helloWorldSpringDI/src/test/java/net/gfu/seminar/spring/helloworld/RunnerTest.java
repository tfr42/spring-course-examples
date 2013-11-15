package net.gfu.seminar.spring.helloworld;

import org.junit.Test;

public class RunnerTest {
	
	@Test
	public void testHelloWorldRunner() {
		HelloWorldRunner.main(null);
	}
	@Test
	public void testHelloWorldRunnerWithSpring() {
		HelloWorldRunnerWithSpring.main(null);
	}
	@Test
	public void testHelloWorldRunnerWithSpringAnnotation() {
		HelloWorldRunnerWithSpringAnnotation.main(null);
	}
}
