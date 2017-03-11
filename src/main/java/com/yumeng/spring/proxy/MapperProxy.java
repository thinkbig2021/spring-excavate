
package com.yumeng.spring.proxy;

import java.io.Serializable;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;

public class MapperProxy<T> implements InvocationHandler, Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@SuppressWarnings("unused")
	private final Class<T> mapperInterface;

	public MapperProxy(Class<T> mapperInterface) {
		this.mapperInterface = mapperInterface;
	}

	public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
		System.out.println("111");
		return method.invoke(this, args);
	}

}
