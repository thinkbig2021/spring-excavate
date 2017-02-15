package com.yumeng.spring.proxy;
public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
	 InvokerProxy proxy = new InvokerProxy(new MyInvoker());
	 Invoker invoker =(Invoker)proxy.getProxy();
	 System.out.println(invoker.invoker1("sss"));

	}

}
