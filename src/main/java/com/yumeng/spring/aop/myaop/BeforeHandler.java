package com.yumeng.spring.aop.myaop;

import java.lang.reflect.Method;

public abstract class  BeforeHandler extends AbstractHandler {
	public abstract void doBeforeHandler(Object proxy,Method method,Object[] args); 

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		doBeforeHandler(proxy,method,args);
		return method.invoke(getTargetObject(), args);
	}

}
