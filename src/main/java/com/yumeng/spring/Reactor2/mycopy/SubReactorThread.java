package com.yumeng.spring.Reactor2.mycopy;


import java.io.IOException;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.SocketChannel;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Set;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.locks.ReentrantLock;

/**
 * Created by yumeng on 2017/5/3.
 */
public class SubReactorThread extends Thread{
    private Selector selector;
    private ExecutorService buissessEexcutePool ;
    private List<NioTask> taskList = new ArrayList<NioTask>();
    private ReentrantLock lock = new ReentrantLock();
    public SubReactorThread(ExecutorService businessExecutorPool){
        this.buissessEexcutePool = businessExecutorPool;
        try {
            selector = Selector.open();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
    public void run(){

         while(!Thread.interrupted()){
             Set<SelectionKey> keys = null;
             try {
                 selector.select(1000);
                 keys = selector.selectedKeys();
                 for(Iterator<SelectionKey> iterator = keys.iterator();iterator.hasNext();){
                     SelectionKey key = iterator.next();
                     iterator.remove();
                     //可写数据到客户端
                     if(key.isWritable()){

                         SocketChannel socketChannel = (SocketChannel) key.channel();
                         ByteBuffer byteBuffer = (ByteBuffer) key.attachment();
                         byteBuffer.flip();
                         socketChannel.write(byteBuffer);

                         System.out.println("服务端向客户端发送数据。。。");

                         socketChannel.register(selector,SelectionKey.OP_READ);

                     }else if(key.isReadable()){
                         SocketChannel socketChannel = (SocketChannel) key.channel();

                         ByteBuffer b  = ByteBuffer.allocate(1024);
                         socketChannel.read(b);
                         dispatch(socketChannel,b);
                     }

                 }

             } catch (IOException e) {
                 e.printStackTrace();
             }
             if(!taskList.isEmpty()){
                 for(Iterator<NioTask> it = taskList.iterator();it.hasNext();){
                     NioTask nioTask = it.next();
                     SocketChannel socketChannel = nioTask.getSocketChannel();
                     try {
                         if(nioTask.getData() != null){
                             socketChannel.register(selector,nioTask.getOps(),nioTask.getData());
                         }else
                             socketChannel.register(selector,nioTask.getOps());
                     } catch (ClosedChannelException e) {
                         e.printStackTrace();
                     }
                     it.remove();

                 }
             }


         }
    }
    public void register(NioTask nioTask){
        if(nioTask != null){
            try {
                lock.lock();
                taskList.add(nioTask);
            } finally {
                lock.unlock();
            }

        }

    }
    public void dispatch(SocketChannel socketChannel, ByteBuffer buffer){
        buissessEexcutePool.submit(new Handler(socketChannel,this,buffer));

    }
}
