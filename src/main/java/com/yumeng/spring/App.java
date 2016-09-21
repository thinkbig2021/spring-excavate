package com.yumeng.spring;

import java.io.File;
import java.util.UUID;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Hello world!
 *
 */
public class App 
{
	public static volatile  int c=0;
	public static AtomicLong cAtomicLong = new AtomicLong(0);
	static CountDownLatch count = new CountDownLatch(10);
    public static void main( String[] args )
    {
         for(int i = 0;i<10;i++){
        	 new MyThread(count).start();
         }
         try {
			count.await();
			System.out.println(cAtomicLong.get());
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
         
    	
    }
}
class MyThread extends Thread{
	CountDownLatch count;
	public MyThread(CountDownLatch count){
		this.count = count;
	}
	public void run(){
		for(int i = 0;i<10000;i++){
			//App.c = App.c+1;
			App.cAtomicLong.getAndAdd(3);
		}
		count.countDown();
	}
}
