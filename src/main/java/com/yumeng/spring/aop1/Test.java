package com.yumeng.spring.aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx=
	            new ClassPathXmlApplicationContext("com/yumeng/spring/aop1/spring-context.xml");
		TestAspect test =(TestAspect)ctx.getBean("testAspect");
		//ctx.publishEvent(event);
	   test.test1("aa");
	}

}
