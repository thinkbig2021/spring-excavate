package com.yumeng.spring.thread;

import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Soulution {

	private static Lock lock = new ReentrantLock();
	private static Condition condition1 = lock.newCondition();
	private static Condition condition2 = lock.newCondition();

	public static void main(String[] args) throws InterruptedException {

		new Thread(new Runnable() {
			@Override
			public void run() {
				try {
					lock.lock();
					condition1.await();
					System.out.println("i was waked up by thread2, i'm going to wake up thread2");
					condition2.signal();
					// lock.unlock();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}).start();

		Thread.sleep(3000);

		new Thread(new Runnable() {
			@Override
			public void run() {
				lock.lock();
				condition1.signal();
				try {
					condition2.await();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				System.out.println("i was waked up by thread1");
			}
		}).start();
	}
}