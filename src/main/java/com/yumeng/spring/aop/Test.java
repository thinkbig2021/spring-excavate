package com.yumeng.spring.aop;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx=
	            new ClassPathXmlApplicationContext("spring/spring-context.xml");
	         
	        AspectJTestBean aspectj=(AspectJTestBean)ctx.getBean("aspectj");
	        aspectj.MyMethod("11");
	}

}
