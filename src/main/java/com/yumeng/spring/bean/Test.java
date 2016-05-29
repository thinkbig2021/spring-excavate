package com.yumeng.spring.bean;

import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.xml.XmlBeanFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.core.io.ClassPathResource;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "spring-context.xml" });
//		ConfigurableListableBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
	//	ConfigurableBeanFactory factory = (ConfigurableBeanFactory)context;
		//factory.addBeanPostProcessor(new MyBeanPostProcessor());
		User2 user = (User2)context.getBean("user2");
		System.out.println(user.getName());
		

	}

}
