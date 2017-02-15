package com.yumeng.spring.thread;

public class MsgService {
	private volatile Object lock;
	
	public void set(String str) throws InterruptedException{
		if(lock != null){
			synchronized (lock) {
				lock.wait();
			}
			//醒来做一些事情
		}else{
			//做一些事情
		}
		
	}
	public String get(String str) throws InterruptedException{
		if(lock != null){
			synchronized (lock) {
				lock.wait();
			}
			//醒来做一些事情
		}else{
			//做一些事情
		}
		return str;
	}
	
	public void doSomething(){
		//这个方法不会被多线程调用
		Object lock1 = new Object();
		synchronized (lock1) {
			//做一个很耗时的操作
			lock.notifyAll();
			lock= null;
		}

	}

}
