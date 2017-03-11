package com.yumeng.spring.lambda;

public class MethodReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		mTest(User::uTest);

	}
	
	public static void mTest(UserInterface s){
		s.test(new User(),"111");
		
	}

}
interface UserInterface{
	
	void test(User user,String string );
}

class User{
	
	public void uTest(String s){
		System.out.println("sfsfsf");
	}
	
}
