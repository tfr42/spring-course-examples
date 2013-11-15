package net.gfu.seminar.spring.helloworld;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.stereotype.Component;

@Aspect
@Component("simpleTraceAdvice")
public class SimpleTraceAdvice {

	private static final Logger LOG = Logger.getLogger(SimpleTraceAdvice.class);

	@SuppressWarnings("unused")
	@Pointcut("execution(* welcome(..))")
	private void welcomePointcut() {
	}
	
	@Before("welcomePointcut()")
	public void enter(JoinPoint joinPoint) {
		String method = joinPoint.getSignature().toLongString();
		Object[] arguments = joinPoint.getArgs();
		Object target = joinPoint.getTarget();
		LOG.debug(String.format(
				"Method '%1$s' entered with arguments %2$s on target %3$s",
				method, Arrays.asList(arguments), target));
	}

	@AfterReturning(pointcut = "welcomePointcut()", returning = "returnValue")
	public void exit(StaticPart staticPart, Object returnValue) {
		String method = staticPart.getSignature().toLongString();
		LOG.debug(String.format("Method '%1$s' returned %2$s", method,
				returnValue));
	}

	@AfterThrowing(pointcut = "welcomePointcut()", throwing = "cause")
	public void fail(StaticPart staticPart, Exception cause) {
		String method = staticPart.getSignature().toLongString();
		LOG.debug(String.format("Method '%1$s' has thrown exception %2$s",
				method, cause));
	}
}
