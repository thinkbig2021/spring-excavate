package com.yumeng.spring.aop1;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
public class Test {

	public static void main(String[] args) {
		ApplicationContext ctx=
	            new ClassPathXmlApplicationContext("com/yumeng/spring/aop1/spring-context.xml");
		com.yumeng.spring.aop1.aop.TestAspect test =(com.yumeng.spring.aop1.aop.TestAspect)ctx.getBean("testAspect1");
		//ctx.publishEvent(event);
	   test.test1("aa");
	}

}
