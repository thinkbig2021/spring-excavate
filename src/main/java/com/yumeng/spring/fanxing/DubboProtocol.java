package com.yumeng.spring.fanxing;

import java.net.URL;

import com.sun.tools.internal.xjc.reader.xmlschema.bindinfo.BIConversion.User;

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
