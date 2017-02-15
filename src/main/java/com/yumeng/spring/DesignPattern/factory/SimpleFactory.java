package com.yumeng.spring.DesignPattern.factory;

public class SimpleFactory {
	 public People getPeople(int sex){
		 if(sex==0)
			 return new Man();
		 return new Women();
	 }

}
