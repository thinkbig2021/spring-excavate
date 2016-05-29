package com.yumeng.spring.propertyeditor;

import java.util.HashMap;
import java.util.Map;

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
				new String[] { "com/yumeng/spring/propertyeditor/spring-context.xml" });
//		ConfigurableListableBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
	//	ConfigurableBeanFactory factory = (ConfigurableBeanFactory)context;
		//factory.addBeanPostProcessor(new MyBeanPostProcessor());
		MyCar user = (MyCar)context.getBean("mycar");
		System.out.println(user.getCar().getBrand());
		Map<String,Class> m = new HashMap<String,Class>();
		m.put("111",  MyCar.class);
		

	}

}
