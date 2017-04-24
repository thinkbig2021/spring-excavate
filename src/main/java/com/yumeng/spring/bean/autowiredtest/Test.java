package com.yumeng.spring.bean.autowiredtest;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "com/yumeng/spring/bean/autowiredtest/spring-context.xml" });
		UserBiz biz = (UserBiz) context.getBean("userBiz");
		biz.dosomething();
		System.out.println(context.getBean("user1"));
		System.out.println(context.getBean("333"));
		System.out.println(context.getBean("myCallBack"));


	}

}
