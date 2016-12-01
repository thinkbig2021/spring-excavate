package com.yumeng.spring.thread;

public class ThreadTest {
	private static String lock = "lock";

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        new A(lock).start();
        new B(lock).start();
	}

}
class A extends Thread {
	String lock ;
	public A(String lock){
		this.lock=lock;
	}
	@Override
	public void run() {
		synchronized (lock) {
			try {
				lock.wait(100000000);
				System.out.println("醒了");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
class B extends Thread {
	String lock ;
	public B(String lock){
		this.lock=lock;
	}
	@Override
	public void run() {
			try {
				sleep(5000);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			System.out.println("开始唤醒");
			synchronized (lock) {
				lock.notify();
			}
	}		
			
}
