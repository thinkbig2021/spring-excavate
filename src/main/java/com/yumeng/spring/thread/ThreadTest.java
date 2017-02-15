package com.yumeng.spring.thread;

public class ThreadTest {

	public static void main(String[] args) throws InterruptedException {
		// TODO Auto-generated method stub

		Thread t = Thread.currentThread();
		Thread t1 = new Thread(new A(t),"ee");
		t1.start();
		t.sleep(1000);
		System.out.println("111");
	}

	static class A implements Runnable {

		Thread t;

		public A(Thread thread) {
			this.t = thread;
		}

		@Override
		public void run() {
			try {
				// TODO Auto-generated method stub
				t.join();
				System.out.println(t.getName());
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

		}

	}

}
