package com.yumeng.spring.atomic;

import java.util.concurrent.atomic.AtomicIntegerFieldUpdater;

public class Updater {
    public static void main(String[] args) throws InterruptedException {
        Container c=new Container();
          for(int i=0;i<100;i++)
          {
              Task t=new Task(c);
              t.start();
              t.join(); //join方法在start后调用，调用线程会等待被调用的线程执行完成后执行

          }
        System.out.println("============="+c.index);
    }
}

class Container{
    public volatile  int index=0;
}

class Task extends Thread {
    private AtomicIntegerFieldUpdater<Container> updater =
            AtomicIntegerFieldUpdater.newUpdater(Container.class,"index");
    private Container c;

    public Task(Container c) {
        this.c = c;
    }
    @Override
    public void run() {
        System.out.println(updater.getAndIncrement(c));
        System.out.println(updater.getAndDecrement(c));
    }
}