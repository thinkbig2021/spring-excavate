package com.yumeng.spring.extendstest;


public class SimpleMappingExceptionResolver extends AbstractHandlerExceptionResolver{

	@Override
	protected void doResolveException(Exception ex) {
		System.out.println("SimpleMappingExceptionResolver.doResolveException");
		
	}

}
