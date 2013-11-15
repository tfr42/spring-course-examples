package net.gfu.seminar.spring.helloworld;

import org.apache.log4j.Logger;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.core.annotation.Order;
import org.springframework.util.StopWatch;

@Aspect
@Order(10)
public class ExecutionTimeAdvice {
	private static final Logger LOG = Logger
			.getLogger(ExecutionTimeAdvice.class);

	@SuppressWarnings("unused")
	@Pointcut("execution(* welcome(..))")
	private void watchTime() {
	}

	@Around("watchTime()")
	public Object around(ProceedingJoinPoint pjp) throws Throwable {
		StopWatch watch = new StopWatch();
		watch.start();
		Object object = pjp.proceed();
		watch.stop();
		LOG.info("Execution time: " + watch.getLastTaskTimeMillis() + "ms");
		return object;
	}

}
