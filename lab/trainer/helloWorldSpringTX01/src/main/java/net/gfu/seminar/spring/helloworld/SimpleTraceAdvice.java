package net.gfu.seminar.spring.helloworld;

import java.util.Arrays;

import org.apache.log4j.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.JoinPoint.StaticPart;
import org.springframework.core.Ordered;

public class SimpleTraceAdvice implements Ordered{
	
	private static final Logger LOG = Logger.getLogger(SimpleTraceAdvice.class);
	private int order;

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
		LOG.debug(String.format("Method '%1$s' has thrown exception %2$s", method,
				cause));
	}

	@Override
	public int getOrder() {
		return order;
	}

	public void setOrder(int order) {
		this.order = order;
	}
}
