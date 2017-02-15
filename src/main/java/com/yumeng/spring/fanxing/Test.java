package com.yumeng.spring.fanxing;

import java.util.ArrayList;
import java.util.List;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		List<?> list1 = new ArrayList<String>();
		List<Object> o  = new ArrayList<Object>();
		o.add(1);
		o.add("222");
		ArrayList<Object> g = new ArrayList<Object>();
		g.add("11111111");
		List<? super Integer> t = g;
		t.add(1);
		b(t);

	}
	public  void a(List<? extends Number> list){
//		list.add(1);
		
	}
	public static void b(List<? super Integer> list){
		//list.add(1);
		System.out.println(list.get(0));
	}
	public static void bb(List<? super B> list){
		//list.add(1);
		list.add(new C());
		System.out.println(list.get(0));
	}
	

}
class A{
	
}
class B extends A{
	
}
class C extends B{
	
}
