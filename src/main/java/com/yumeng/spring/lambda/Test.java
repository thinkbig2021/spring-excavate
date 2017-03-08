package com.yumeng.spring.lambda;

import java.util.Arrays;
import java.util.List;
import java.util.concurrent.Callable;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		new Thread(()->{System.out.println("hahah");}).start();
		
		   List<String> execStrs = Arrays.asList("1","2");
		//   execStrs.stream().collect(collector)
		   execStrs.forEach(x->{System.out.println(x);});
		   String string =  "1";
		   String []datas = new String[] {"peng","Zhao","li"};
		   Arrays.sort(datas,String::compareToIgnoreCase);
		   Callable<String> c = () -> "done";
		   MyFun fun = System.out::println;
		   fun.fun("sfsf");

	}
	interface MyFun{
		void fun(String s);
	}
	interface MyFun1{
	}

}
