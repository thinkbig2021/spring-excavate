package com.yumeng.spring.map;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
import java.util.TreeMap;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentSkipListMap;

public class ConcurrentHashMapTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Map<String,String> map = new LinkedHashMap<String,String>();
		map.put("bee", "1");
		map.put("av", "2");
		map.put("d3", "3");
		map.put("aaa", "4");
		for(Map.Entry<String, String> entry: map.entrySet()){
			System.out.println(entry.getKey());
		}

	}

}
