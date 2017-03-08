package com.yumeng.spring.guava;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.TimeUnit;

import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.google.common.collect.Lists;
import com.sun.org.apache.bcel.internal.generic.RETURN;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Cache<Integer, String> cache = CacheBuilder.newBuilder().maximumSize(1000).expireAfterAccess(2, TimeUnit.HOURS)
				.build();
		for(int i=0;i<10;i++){
			try {
				cache.get(i, new Callable<String>() {

					@Override
					public String call() throws Exception {
						// TODO Auto-generated method stub
						System.out.println("11");
						return UUID.randomUUID().toString();
					}
				});
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.out.println(cache.getIfPresent(12));
		
     List list =   Lists.newArrayList("1","2","3");
		System.out.println(String.join(",", list));
		
	}

}
