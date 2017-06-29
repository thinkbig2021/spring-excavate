package com.yumeng.spring.Reactor2.mycopy;


import com.yumeng.spring.Reactor2.NioTask;
import com.yumeng.spring.Reactor2.SubReactorThread;

import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * Created by yumeng on 2017/5/3.
 */
public class SubReactorThreadGroup {
    private AtomicInteger requestCounter = new AtomicInteger();

    private final int nioThreadCount; //线程池io线程数量

    private SubReactorThread[] nioThreads ;

    private static final int DEFAULT_NIO_THREAD_COUNT;

    private ExecutorService businessExecutePool;//业务线程池
    static{
        DEFAULT_NIO_THREAD_COUNT = 4;
    }
    public SubReactorThreadGroup(){
        this(DEFAULT_NIO_THREAD_COUNT);
    }
    public SubReactorThreadGroup(int nioThreadCount) {
        if(nioThreadCount < 1){
            nioThreadCount = DEFAULT_NIO_THREAD_COUNT;
        }
        businessExecutePool = Executors.newFixedThreadPool(nioThreadCount);
        this.nioThreads = new SubReactorThread[nioThreadCount];
        for(int i = 0;i< nioThreadCount;i++){
            this.nioThreads[i] = new SubReactorThread(businessExecutePool);
            this.nioThreads[i].start();
        }
        this.nioThreadCount = nioThreadCount;
    }
    public void dispatch(SocketChannel sc){
        if(sc != null){
            next().register(new NioTask(sc, SelectionKey.OP_READ));
        }
    }
    public SubReactorThread next(){

        return nioThreads[requestCounter.getAndIncrement()%DEFAULT_NIO_THREAD_COUNT];
    }
}
