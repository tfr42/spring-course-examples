package net.gfu.seminar.spring.helloworld;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.ProceedingJoinPoint;

public class SimpleTraceAdvice {
	
	private static final Logger LOG = Logger.getLogger(SimpleTraceAdvice.class);

	public void enter(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toLongString();
		Object[] arguments = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		LOG.debug(String.format("Method '%1$s' entered with arguments %2$s on target %3$s", method, Arrays.asList(arguments), target));
		}

	public void exit(StaticPart staticPart, Object returnValue) {
		String method = staticPart.getSignature().toLongString();
		LOG.debug(String.format("Method '%1$s' returned %2$s", method,
				returnValue));
	}

	public void fail(StaticPart staticPart, Exception cause) {
		String method = staticPart.getSignature().toLongString();
		LOG.debug(String.format("Method '%1$s' has thrown the following exception %2$s", method,
				cause));
	}
	
	public void proceed(ProceedingJoinPoint pjp) {
		System.out.println("ProceedingJoinPoint: target method:" + pjp.getSignature());
		try {
			Object[] args = pjp.getArgs();
			args[0] = new GuestImpl("Foo","42");
			Object returnValue = pjp.proceed(args);
			System.out.println(returnValue);
		} catch (Throwable e) {
			e.printStackTrace();
		}
	}
}
