package com.yumeng.spring.lambda;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by yumeng on 2017/3/12.
 */
public class ListTest {

    public static void main(String[] args) {
        List<User1> user1List = new ArrayList<>();
        User1 u1 = new User1(1, "yumeng1");
        User1 u4 = new User1(4, "zhangsan1");
        User1 u5 = new User1(5, "zhangsan2");
        User1 u2 = new User1(2, "zhangsan3");
        User1 u3 = new User1(3, "zhangsan4");
        user1List.add(u1);
        user1List.add(u4);
        user1List.add(u5);
        user1List.add(u2);
        user1List.add(u3);


        List<User1> list1 = user1List.stream().filter((u) ->
                u.getName().startsWith("yu")).collect(Collectors.toList());
        System.out.println(list1.size());

         List<User1> l = user1List.stream().sorted(Comparator.comparing(user->user.getAge())).collect(Collectors.toList());

        Map<Integer,User1> maps = user1List.stream().collect(Collectors.toMap(User1::getAge, user1->user1));

       Optional<User1> user = user1List.stream().max((x, y)->{

            if(x.getAge()>y.getAge()){ return 1;
            }else{ return -1;}

        });

        System.out.println(user.get().getAge());



    }
    public static <T,V> T get(T t,V v){
        return t;
    }
}
