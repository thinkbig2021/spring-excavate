package com.yumeng.spring;

import java.util.UUID;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args )
    {
        System.out.println( "Hello World!" );
        System.out.println("welcome to github");
        
        Integer   i = 1111;
        Integer j = 1111;
        System.out.println(i.equals(j));
        while(true){
        	
        	String lock = UUID.randomUUID().toString();
        	lock.intern();
        	
        }
   

    }
}
