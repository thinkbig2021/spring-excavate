package com.yumeng.spring.reflect;

public class SubClass extends SuperClass{
	private String password;
	public void b(){
		
		
	}
	public String getPassword() {
		return password;
	}
	@MyAnnotation(name="test",hasText="true",param={"1","2"})
	public void setPassword(String password) {
		this.password = password;
	}
	public Integer getValue(int i){
		return i;
	}
	private Boolean isTrue(){
		return true;
	}
	 public static class InnerClass{
	    	
	    }
}
