package com.yumeng.spring.utiltest;

import java.util.ArrayList;
import java.util.List;

import com.sun.security.jgss.ExtendedGSSContext;

public class Test1 {

	public static void main(String[] args) {
		
       List<Object> aList = new ArrayList<>();
	}
	private static <T> List<T> parseSqlResultToListObject(String s,Class<? extends T> cls) throws Exception{
		List<Object> list = new ArrayList<Object>();
		List<T> list2 = new ArrayList<T>();
		if(list.size()==0){
			return null;
		}else{
			return (List<T>) list;
		}
	}

}
