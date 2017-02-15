package com.yumeng.spring.thread;

import com.google.common.util.concurrent.RateLimiter;

public class RateLimterTest {
	final static RateLimiter limiter = RateLimiter.create(1);
	public static void main(String[] args) {
		// TODO Auto-generated method stub
	
        for(int i = 0 ; i< 500;i++){
        	new Thread(new Runnable() {
				
				@Override
				public void run() {
					RateLimterTest.a();
					
				}
			}).start();
        }

	}
	public static void a(){
		limiter.acquire();
		System.out.println("睡一会儿");
		try {
			Thread.sleep(50000);
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
