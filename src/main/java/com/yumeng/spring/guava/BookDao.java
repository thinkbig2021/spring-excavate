package com.yumeng.spring.guava;

import java.util.ArrayList;
import java.util.List;

public class BookDao {  
    public Object executeSQL() {  
        System.out.println("此处调用了Dao方法。executeSQL");  
        List<Book> books = new ArrayList<Book>();  
        for (int i = 0; i < 3; i++) {  
            Book b = new Book(i);  
            books.add(b);  
        }  
  
  
        return books;  
    }  
}  