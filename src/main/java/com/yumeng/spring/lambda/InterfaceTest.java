package com.yumeng.spring.lambda;


import java.util.ArrayList;
import java.util.List;

public class InterfaceTest {

    public static void main(String[] args) {
        // TODO Auto-generated method stub
        register((x, y) -> {
        });
        List s = new ArrayList<>();
        Interface2 t = String::compareTo;
        t.test("111", "222");
      //  Interface3 tt = InterfaceTest::a;

        Interface4 t5 =InterfaceTest::a;

    }


    public static void register(Interface1 in) {

    }
//	public static void register(Interface2 in) {
//
//	}

//	public void register(Interface1 in) {
//
//	}

//    public  static String a(int a, int b) {
//          return null;
//    }
    public   Double a(int a, int b) {
        return null;
    }
     interface Interface3 {

        String test(int i,int j);
    }
    interface Interface4 {

        void test(InterfaceTest t,int i,int j);
    }
}
