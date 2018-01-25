package net.gfu.seminar.spring.data;

import org.springframework.aop.Advisor;
import org.springframework.aop.aspectj.AspectJExpressionPointcut;
import org.springframework.aop.interceptor.CustomizableTraceInterceptor;
import org.springframework.aop.support.DefaultPointcutAdvisor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@Configuration
@EnableAspectJAutoProxy
public class SpringDataExecutionLoggingConfiguration {

  @Bean
  public CustomizableTraceInterceptor customizableTraceInterceptor() {

    CustomizableTraceInterceptor customizableTraceInterceptor = new CustomizableTraceInterceptor();
    //customizableTraceInterceptor.setUseDynamicLogger(true);
    customizableTraceInterceptor.setLoggerName(this.getClass().getName());
    customizableTraceInterceptor.setExitMessage("Executed $[methodName] in $[invocationTime] ms");
    return customizableTraceInterceptor;
  }

  @Bean
  public Advisor advisor() {

    AspectJExpressionPointcut pointcut = new AspectJExpressionPointcut();
    pointcut.setExpression("execution(public * org.springframework.data.repository.CrudRepository+.*(..))");
    return new DefaultPointcutAdvisor(pointcut, customizableTraceInterceptor());
  }
}