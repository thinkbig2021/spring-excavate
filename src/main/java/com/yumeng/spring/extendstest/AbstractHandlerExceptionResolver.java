package com.yumeng.spring.extendstest;

public abstract class AbstractHandlerExceptionResolver implements HandlerExceptionResolver {

	@Override
	public void resolveException(Exception ex) {
		System.out.println("AbstractHandlerExceptionResolver.doResolveException");
		 doResolveException(ex);
	}
	protected abstract void doResolveException(Exception ex);

}
