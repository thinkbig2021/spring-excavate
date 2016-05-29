package com.yumeng.spring.aop;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.aop.aspectj.MethodInvocationProceedingJoinPoint;
 
@Aspect
public class MyAspectJ {
 /*
  * 以下都是正确的切入点表达式
  @Pointcut("execution(* AspectJTestBean.*(..))")
  @Pointcut("execution(void com.laoyangx.bean.chapter11.*.*(..))")
  @Pointcut("execution(* com.laoyangx.bean.chapter11..*.*(..))")   
  @Pointcut("execution(* com.laoyangx.bean.chapter11.AspectJTestBean.*(..))")
*/ 
  @Pointcut("execution(* com.yumeng.spring.aop..*(..))")
 public void Before(){}
  
 @Before("Before()")
 public void BeforeAspectJTestBean(JoinPoint point){
      
	 MethodInvocationProceedingJoinPoint o = (MethodInvocationProceedingJoinPoint)point;
	 System.out.println(point.getClass());
     AspectJTestBean aspectj=(AspectJTestBean)point.getTarget();
     try {
	//	o.proceed();
	} catch (Throwable e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	}
      
    // System.out.println(point.getArgs());
     System.out.println(aspectj.getClass().getName()+"."
             +point.getSignature().getName()+"(),将要运行!");
 } 
 @AfterThrowing(pointcut="Before()", throwing="ex")
	public void afterThrow(JoinPoint joinPoint, Exception ex){
	 System.out.println("hello");
	ex.printStackTrace();
	}
 @AfterReturning(pointcut="Before()",returning="str")
	public void afterThrow(JoinPoint joinPoint, String str){
	 System.out.println(str);
	}
}