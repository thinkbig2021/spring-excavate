package com.yumeng.spring.aop1;

public class TestAspect {
	public void test(String myname){
		
		//System.out.println("这是一个测试");
		throw new RuntimeException("哈哈哈");
	}
   public String test1(String myname){
		
		return myname;
	}
   public void test2(String arg1){
		
		System.out.println("9999999");
	}
}
