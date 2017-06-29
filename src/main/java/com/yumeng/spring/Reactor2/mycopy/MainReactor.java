package com.yumeng.spring.Reactor2.mycopy;

import java.io.IOException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

/**
 * Created by yumeng on 2017/5/3.
 */
public class MainReactor implements Runnable {
    private Selector selector;
    private SubReactorThreadGroup group;

    public MainReactor(ServerSocketChannel ssc) {
        try {
            selector = Selector.open();
            ssc.register(selector, SelectionKey.OP_ACCEPT);
        } catch (IOException e) {
            e.printStackTrace();
        }
        group = new SubReactorThreadGroup(4);

    }

    @Override
    public void run() {

        while (!Thread.interrupted()) {

            Set<SelectionKey> opts = null;
            try {
                selector.select(1000);
                opts = selector.selectedKeys();

                for(Iterator<SelectionKey> iterator = opts.iterator();iterator.hasNext();){
                    SelectionKey key = iterator.next();
                    iterator.remove();
                    if(key.isAcceptable()){
                        System.out.println("客户端连接");
                        ServerSocketChannel ssc = (ServerSocketChannel) key.channel();
                        ssc.configureBlocking(false);
                        SocketChannel socketChannel = ssc.accept();
                        group.dispatch(socketChannel);
                    }

                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
