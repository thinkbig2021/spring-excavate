package com.yumeng.spring.proxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class InvokerProxy implements InvocationHandler{

	private Object proxy1;
	public InvokerProxy(Object target){
		this.proxy1 = target;
	}
	@Override
	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println(proxy.getClass());
		System.out.println("开始执行代理方法");
	    Object o = method.invoke(proxy1, args);
		return o;
		
	}
	public Object getProxy(){
		return Proxy.newProxyInstance(this.getClass().getClassLoader(), new Class[]{Invoker.class}, this);
		
				
	}

}
