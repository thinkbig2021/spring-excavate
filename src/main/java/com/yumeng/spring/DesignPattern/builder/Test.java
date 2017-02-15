package com.yumeng.spring.DesignPattern.builder;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
        PersonBuilder builder = new ConcretePersonbuilder();
        builder.buildEye("1").buildHair("222").getPerson();
		 
	}

}
