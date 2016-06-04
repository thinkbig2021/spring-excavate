package com.yumeng.spring.aop1;

import java.lang.reflect.Method;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;

public class MyAspectj {
	public void before(JoinPoint point){
		 Signature signature = (Signature) point.getSignature();  
	        System.out.println("DeclaringType:" + signature.getDeclaringType());   
	        System.out.println("DeclaringTypeName:" + signature.getDeclaringTypeName());  
	        System.out.println("Modifiers:" + signature.getModifiers());  
	        System.out.println("Name:" + signature.getName());  
	        System.out.println("LongString:" + signature.toLongString());  
	        System.out.println("ShortString:" + signature.toShortString());  
		System.out.println("方法执行开始");
		
	}
	public void after(String t,String arg2,String arg3){
		System.out.println("方法执行完毕");
	}
	public void after1(JoinPoint point){
		System.out.println("after");
	}

	public void around(ProceedingJoinPoint point) throws Throwable{
		System.out.println("around");
		//point.proceed();
	}
	public void afterreturn(String rtv,String myname){
		System.out.println(rtv);
		System.out.println(11);
		
	}
	public void afterThrow(Throwable ex){
		System.out.println("afterThrow");;
	}
}
