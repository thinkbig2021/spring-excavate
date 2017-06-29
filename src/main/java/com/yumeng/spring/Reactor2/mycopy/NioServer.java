package com.yumeng.spring.Reactor2.mycopy;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.channels.ServerSocketChannel;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * Created by yumeng on 2017/5/3.
 */
public class NioServer {
    public static final int DEFAULT_PORT = 9090;

    public static void main(String[] args) {

    }
    private static class Acceptor implements Runnable{

        private static ExecutorService mainReactor = Executors.newSingleThreadExecutor();
        @Override
        public void run() {
            try {
                ServerSocketChannel ssc = ServerSocketChannel.open();
                ssc.configureBlocking(false);
                ssc.bind(new InetSocketAddress(DEFAULT_PORT));
                dispath(ssc);
            } catch (IOException e) {
                e.printStackTrace();
            }

        }

        private void dispath(ServerSocketChannel ssc) {
            mainReactor.submit(new MainReactor(ssc));
        }
    }

}
