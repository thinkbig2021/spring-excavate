package com.yumeng.spring.thread;

import java.util.concurrent.TimeUnit;

public class Test {

	public static void main(String[] args) {
		long s = System.currentTimeMillis();
		System.out.println(getString(10l));
		long end = System.currentTimeMillis();
		System.out.println((end-s)/1000);
	}
	public static String getString(long timeout){
		String str = null;
		long endTime = System.currentTimeMillis()+TimeUnit.SECONDS.toMillis(timeout);
		while(System.currentTimeMillis()<endTime){
            str = String.valueOf(System.currentTimeMillis());
		}
		return str;
	}

}
