package com.yumeng.spring.utiltest;

import java.util.Map;

import org.springframework.beans.factory.BeanFactoryUtils;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class Test {
public static void main(String[] args) {
	ApplicationContext ctx=
            new ClassPathXmlApplicationContext("com/yumeng/spring/utiltest/spring-context.xml");
	Map<String,HandlerMapping> maps = BeanFactoryUtils
	.beansOfTypeIncludingAncestors(ctx, HandlerMapping.class, false, false);
	System.out.println(maps.size());
	HandlerMapping t = new MyHandler2();
}
}
