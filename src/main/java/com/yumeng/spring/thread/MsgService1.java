package com.yumeng.spring.thread;

import java.util.concurrent.locks.ReadWriteLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class MsgService1 {
	private static ReadWriteLock lock = new ReentrantReadWriteLock(); 
	
	public void set(String str) throws InterruptedException{
		lock.readLock().lock();
		System.out.println("set 操作10秒");
		Thread.sleep(10000);
		lock.readLock().unlock();
		
	}

	public String get(String str,int i) throws InterruptedException{
		lock.readLock().lock();
		System.out.println("get 操作10秒");
		Thread.sleep(10000);
		if(i != 4){
			lock.readLock().unlock();
		}
	//	lock.readLock().unlock();
		System.out.println("获取读锁的同时试试写锁");
		doSomething() ;
		return str;
	}
	
	public void doSomething() throws InterruptedException{
		lock.writeLock().lock();
		System.out.println("doSomething 操作2秒");
		Thread.sleep(2000);
		lock.writeLock().unlock();

	}

}
