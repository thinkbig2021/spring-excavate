package com.yumeng.spring.bean.init;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {

	public static void main(String[] args) {
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] { "com/yumeng/spring/bean/init/spring-context.xml" });
//		ConfigurableListableBeanFactory factory = new XmlBeanFactory(new ClassPathResource("spring-context.xml"));
	//	ConfigurableBeanFactory factory = (ConfigurableBeanFactory)context;
		//factory.addBeanPostProcessor(new MyBeanPostProcessor());

		UserHolder userHolder = (UserHolder) context.getBean("userHolder");
		System.out.println(userHolder.isTrue());

		

	}

}
