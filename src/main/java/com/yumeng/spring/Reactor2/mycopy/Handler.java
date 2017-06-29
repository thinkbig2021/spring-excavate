package com.yumeng.spring.Reactor2.mycopy;

import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.SocketChannel;

/**
 * Created by yumeng on 2017/5/4.
 * 此类为业务线程，处理业务相关信息
 */
public class Handler implements  Runnable{

    private static final byte b[] = "hello,服务器收到你的信息".getBytes();
    private  SubReactorThread subReactorThread;
    private SocketChannel socketChannel;
    private ByteBuffer buffer ;

    public Handler(SocketChannel socketChannel,SubReactorThread subReactorThread,ByteBuffer buffer){
        this.buffer=buffer;
        this.socketChannel = socketChannel;
        this.subReactorThread = subReactorThread;
    }
    @Override
    public void run() {
        System.out.println("业务在handler中开始执行。。。");
        buffer.put(b);
        subReactorThread.register(new NioTask(socketChannel, SelectionKey.OP_WRITE,buffer));
        System.out.println("业务在handler中结束。。。");


    }
}
