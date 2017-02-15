package com.yumeng.spring.proxy;

public class ProxyTest {
	public static void main(String[] args) {
		MapperProxyFactory<HelloService> factory = new MapperProxyFactory<>(HelloService.class);
		HelloService service = factory.newInstance(new MapperProxy<HelloService>(HelloService.class));
		service.hashCode();

	}
}
