package com.yumeng.spring.extendstest;

public class Test {

	public static void main(String[] args) {
		HandlerExceptionResolver handler = new ControllerMappingExceptionResolver();
		handler.resolveException(new NumberFormatException());

	}

}
