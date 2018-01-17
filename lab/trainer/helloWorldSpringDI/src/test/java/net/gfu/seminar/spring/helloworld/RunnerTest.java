package net.gfu.seminar.spring.helloworld;

import org.junit.Test;

public class RunnerTest {
	
	@Test
	public void testApplicationWithPlainJava() {
		ApplicationWithPlainJava.main(null);
	}
	@Test
	public void testApplicationWithSpring() {
		ApplicationWithSpring.main(null);
	}
	@Test
	public void testApplicationWithSpringJavaConfig() {
		ApplicationWithSpringJavaConfig.main(null);
	}
}
