package com.yumeng.spring.aop.myaop;

import java.util.Arrays;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Calculator targetObject = new CalculatorImpl();
		List<AbstractHandler> hanlders = Arrays.asList(new AfterHandlerImpl(),new BeforHandlerImpl());
		Calculator proxyObject  = (Calculator)ProxyFactory.getProxy(targetObject, hanlders);
		proxyObject.calculate(2, 2);

	}

}
