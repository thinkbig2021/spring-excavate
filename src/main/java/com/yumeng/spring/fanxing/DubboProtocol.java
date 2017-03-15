package com.yumeng.spring.fanxing;


import com.yumeng.spring.bean.User;

import java.net.URL;

public class DubboProtocol {
	public <T> Invoker<T> refer(Class<T> serviceType, URL url) {
		// create rpc invoker.
		DubboInvoker<T> invoker = new DubboInvoker<T>();
		return invoker;
	}
	public static void main(String[] args) {
		DubboProtocol dubbo = new DubboProtocol();
		Invoker<User> userInvoker = dubbo.refer(User.class, null);
	}

}
