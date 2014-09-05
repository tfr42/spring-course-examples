package net.gfu.seminar.spring.helloworld;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;

@Aspect
public class MethodCallCounterAdvice {
	private static final Logger LOG = Logger
			.getLogger(MethodCallCounterAdvice.class);
	private Map<String, Integer> counter = new HashMap<String, Integer>();

	@Pointcut("execution(* net.gfu.seminar.spring.helloworld.Greeting.welcome())")
	public void serviceMethods() {
	}

	@Around("serviceMethods()")
	public Object profile(ProceedingJoinPoint pjp) throws Throwable {
		LOG.info("Going to call the method '" + pjp.getSignature().getName()+"'");
		Signature signature = pjp.getSignature();
		Integer counterForMethod = counter.get(signature.toLongString());
		if (counterForMethod == null) {
			counterForMethod = 0;
		}
		final Object output = pjp.proceed();
		counterForMethod = Integer.valueOf(counterForMethod.intValue() + 1);
		counter.put(signature.toLongString(), counterForMethod);
		LOG.info(String.format("This is call %1$s to '%2$s'", counterForMethod,
				signature));
		return output;
	}
	
	public Map<String, Integer> getCounter() {
		return Collections.unmodifiableMap(this.counter);
	}
}