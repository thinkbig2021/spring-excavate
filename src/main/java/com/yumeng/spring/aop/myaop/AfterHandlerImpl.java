package com.yumeng.spring.aop.myaop;

import java.lang.reflect.Method;

public class AfterHandlerImpl extends AfterHandler {

	@Override
	public void doAfterHandler(Object proxy, Method method, Object[] args) {
		// TODO Auto-generated method stub
       System.out.println("invoke after targetObject");
	}

}
