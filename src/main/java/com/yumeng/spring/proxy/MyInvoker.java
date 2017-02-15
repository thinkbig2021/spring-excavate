package com.yumeng.spring.proxy;

public class MyInvoker implements Invoker {

	@Override
	public Object invoker1(String s) {
		
		return s;
	}

}
