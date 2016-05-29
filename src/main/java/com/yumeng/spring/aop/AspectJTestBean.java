package com.yumeng.spring.aop;
public class AspectJTestBean {
 
 public String MyMethod(String s){
     System.out.println(this.getClass().getName()+
             ".MyMethod() is run!");
     return "111";
 }     
}