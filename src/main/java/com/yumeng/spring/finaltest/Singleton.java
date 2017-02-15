package com.yumeng.spring.finaltest;

public class Singleton {

	private Singleton(){
		
	}
	public static Singleton getSingleton(){
		return SingletonHolder.singleton;
	}
	private static final class SingletonHolder{
		public static Singleton singleton ;
		static{
			singleton = new Singleton();
		}
		
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
           Singleton t = Singleton.getSingleton();
           Singleton tt = Singleton.getSingleton();
           System.out.println(t==tt);
	}

}
