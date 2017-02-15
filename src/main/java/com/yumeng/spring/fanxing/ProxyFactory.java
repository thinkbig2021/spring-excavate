package com.yumeng.spring.fanxing;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;
@Component
public class ProxyFactory<T> {
	public ProxyFactory(){
		System.out.println("泛型类初始化成功");
	}
	public List<T>  getList(Class<T>  clz){
		return new ArrayList<T>();
	}

}
