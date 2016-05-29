package com.yumeng.spring.extendstest;

import java.io.IOException;

public abstract class AbstractAccessExceptionResolver extends SimpleMappingExceptionResolver {

	@Override
	public void resolveException(Exception ex) {
		System.out.println("AbstractAccessExceptionResolver.resolveException");
		if (isSupportException(ex)) {
	
				doInternalResolveException(ex);
			
		} else {

			doInternalResolveException(ex);
		}
	}

	protected abstract void doInternalResolveException(Exception ex) ;

	protected abstract boolean isSupportException(Exception ex);

}
