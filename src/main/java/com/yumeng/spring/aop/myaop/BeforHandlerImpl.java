package com.yumeng.spring.aop.myaop;

import java.lang.reflect.Method;

public class BeforHandlerImpl extends BeforeHandler {

	@Override
	public void doBeforeHandler(Object proxy, Method method, Object[] args) {
		// TODO Auto-generated method stub
		System.out.println("invoke before targetObject");

	}

}
