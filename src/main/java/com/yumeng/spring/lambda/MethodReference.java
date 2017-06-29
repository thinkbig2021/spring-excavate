package com.yumeng.spring.lambda;

public class MethodReference {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		User u = new User();
		mTest(User::uTest);
		mTest(User::eTest);
		mTest(u::dTest);


	}
	
	public static void mTest(UserInterface s){
		s.test(new User(),"111");
		
	}

}
interface UserInterface{
	
	void test(User user,String string );
}
interface UserInterface1{

	void test(User user,String string );
}

class User{

	public void uTest(String s){
		System.out.println("sfsfsf");
	}
	public static void bTest(String s){
		System.out.println("sfsfsf");
	}
	public void cTest(String s,String a){
		System.out.println("sfsfsf");
	}
	public void dTest(User u){
		System.out.println("sfsfsf");
	}

	public void dTest(User user, String s) {
	}
	public static void eTest(User user, String s){
		System.out.println("sfsfsf");
	}
}
