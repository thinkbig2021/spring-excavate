package com.yumeng.spring.bean;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.BeanFactoryPostProcessor;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;

public class MyBeanFactoryPostProcessor implements BeanFactoryPostProcessor{

	@Override
	public void postProcessBeanFactory(ConfigurableListableBeanFactory beanFactory) throws BeansException {
		// TODO Auto-generated method stub
		System.err.println("MyBeanFactoryPostProcessor");
//		User2 u = (User2)beanFactory.getBean("user2");
//		u.setName("hahahaa");
		
	}

}
