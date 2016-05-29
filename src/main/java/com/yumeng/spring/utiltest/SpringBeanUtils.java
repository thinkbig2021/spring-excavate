package com.yumeng.spring.utiltest;


import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

/**
 * <pre>
 * 用于从Spring容器中获取对象的工具类
 * @ClassName: SpringBeanUtils
 * @author yumeng
 * @date 2015年1月20日 上午2:31:18
 */
public class SpringBeanUtils implements ApplicationContextAware {

	private static ApplicationContext	applicationContext;

	@Override
	public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
		SpringBeanUtils.applicationContext = applicationContext;
	}
	
	@SuppressWarnings("unchecked")
	public static <T> T get(String name) {
		return (T) applicationContext.getBean(name);
	}

	public static <T> T get(Class<T> clazz) {
		return (T) applicationContext.getBean(clazz);
	}
}
