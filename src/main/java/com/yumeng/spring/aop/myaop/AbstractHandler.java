package com.yumeng.spring.aop.myaop;

import java.lang.reflect.InvocationHandler;

public abstract class AbstractHandler implements InvocationHandler{
	private Object targetObject;

	public Object getTargetObject() {
		return targetObject;
	}

	public void setTargetObject(Object targetObject) {
		this.targetObject = targetObject;
	}
	

}
