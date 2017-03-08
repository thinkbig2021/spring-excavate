package com.yumeng.spring.lambda;

public interface DefaultImpI {
	void say();
	default void test(){
		System.out.println("test");
	}

}
