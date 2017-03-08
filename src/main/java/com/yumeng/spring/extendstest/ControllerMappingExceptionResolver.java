package com.yumeng.spring.extendstest;

import java.io.IOException;


public class ControllerMappingExceptionResolver extends SecurityAccessExceptionResolver {

	@Override
	protected void doInternalResolveException(Exception ex) {
		System.out.println("ControllerMappingExceptionResolver.doInternalResolveException");
		if (super.isSupportException(ex)) {
			super.doInternalResolveException(ex);
		}

		else {
			 super.doResolveException(ex);
		}
	}

	@Override
	protected boolean isSupportException(Exception ex) {
		System.out.println("ControllerMappingExceptionResolver.isSupportException");
		if (super.isSupportException(ex)) {
			return true;
		}

//		// 判读是否是service层异常
//		if (ex instanceof ServiceException) {
//			return true;
//		}

		return false;
	}


}
