package com.yumeng.spring.extendstest;

import java.io.IOException;


public class SecurityAccessExceptionResolver extends AbstractAccessExceptionResolver{

	@Override
	protected void doInternalResolveException(Exception ex)  {
		System.out.println("处理空指针异常");
		System.out.println("SecurityAccessExceptionResolver.doInternalResolveException");
		
	}

	@Override
	protected boolean isSupportException(Exception ex) {
		System.out.println("SecurityAccessExceptionResolver.isSupportException");
		return ex instanceof NullPointerException;
	}

	

}
