package com.yumeng.spring.aop.myaop;

import java.lang.reflect.Method;

public abstract class AfterHandler extends AbstractHandler {
	public abstract void doAfterHandler(Object proxy,Method method,Object[] args);

	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		// TODO Auto-generated method stub
		Object result = method.invoke(getTargetObject(), args);
		doAfterHandler(proxy, method, args);
		return result;
	}

}
