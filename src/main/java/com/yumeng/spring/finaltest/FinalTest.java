package com.yumeng.spring.finaltest;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class FinalTest {

	private static  Map<String,String> map = new HashMap<String,String>();
	private final static Map<String,String> map1 = Collections.unmodifiableMap(map);
	public static void main(String[] args) {
		map1.put("1","1");
		map.put("1","1");

	}

}
