package com.yumeng.spring.thread;

public class ReadWriteLockTest {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MsgService1 service = new MsgService1();
		
	
		for(int i = 0;i< 5;i++){
			ThreadRead read = new ThreadRead(service,i);
			read.start();
		}
//		for(int i = 0;i< 5;i++){
//			ThreadRead1 read1 = new ThreadRead1(service);
//			read1.start();
//		}
//		ThreadWrite write = new ThreadWrite(service);
//		write.start();
		

	}
	static class ThreadRead extends Thread{
		private MsgService1 service ;
		private int i;
		public  ThreadRead(MsgService1 service,int i){
			this.service= service;
			this.i= i;
		}
		public void run(){
			try {
				service.get("111",i);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static class ThreadRead1 extends Thread{
		private MsgService1 service ;
		public  ThreadRead1(MsgService1 service){
			this.service= service;
		}
		public void run(){
			try {
				service.set("222");
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	static class ThreadWrite extends Thread{
		private MsgService1 service ;
		public  ThreadWrite(MsgService1 service){
			this.service= service;
		}
		public void run(){
			try {
				service.doSomething();
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
