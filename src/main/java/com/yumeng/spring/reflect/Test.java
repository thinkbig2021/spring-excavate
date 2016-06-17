package com.yumeng.spring.reflect;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

import com.yumeng.spring.reflect.SubClass.InnerClass;

public class Test {

	public SubClass getSubClass() {
		return subClass;
	}
	public void setSubClass(SubClass subClass) {
		this.subClass = subClass;
	}
	public  SubClass subClass;
	public static void main(String[] args) throws NoSuchFieldException, SecurityException {
		// TODO Auto-generated method stub
		SuperClass sub = new SubClass();
		System.out.println(SubClass.class.getCanonicalName());
		System.out.println(InnerClass.class.getName());
		System.out.println(InnerClass.class.getCanonicalName());
		int i[] = new int[]{1,2};

		Class cs[] = SubClass.class.getClasses();
	
		System.out.println(cs.length);
		for(Class c : cs){
			System.out.println(c.getName());
		}
		System.out.println(sub.getClass().getDeclaringClass());
		System.out.println(SubClass.class.getField("username"));
		//System.out.println(SubClass.class.getField("password"));
		System.out.println(SubClass.class.getDeclaredField("password"));
		System.out.println(SubClass.class.isAssignableFrom(SuperClass.class));
		System.out.println(SuperClass.class.isAssignableFrom(SubClass.class));
		System.out.println(SubClass.class.getSuperclass());
		System.out.println(SubClass.class.getGenericSuperclass());
		System.out.println(SubClass.class.isMemberClass());
		Field fs [] = SubClass.class.getDeclaredFields();
		for(Field f : fs){
			System.out.println(f.getName());
		}
		List<Method> m = new ArrayList<Method>();
		getParentClassMothds(m,SubClass.class);
		

	}
	private static List< Method > getParentClassMothds ( List< Method > list, Class clazz )
	{
		Method[] methods = clazz.getDeclaredMethods ( );
		for ( Method method : methods )
		{
			list.add ( method );
		}
		if ( clazz.getSuperclass ( ) == Object.class )
		{
			return list;
		}
		getParentClassMothds ( list, clazz.getSuperclass ( ) );
		return list;
	}

}
