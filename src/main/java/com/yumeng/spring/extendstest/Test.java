package com.yumeng.spring.extendstest;

import com.alibaba.druid.pool.vendor.NullExceptionSorter;
import com.zhibitech.framework.core.exception.ServiceException;

public class Test {

	public static void main(String[] args) {
		HandlerExceptionResolver handler = new ControllerMappingExceptionResolver();
		handler.resolveException(new NumberFormatException());

	}

}
