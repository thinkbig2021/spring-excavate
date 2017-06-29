package com.yumeng.spring.Reactor;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.net.ServerSocket;
import java.nio.ByteBuffer;
import java.nio.channels.ClosedChannelException;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
 
 
/**
 * @author marlonyao<yaolei135@gmail.com>
 *
 */
public class EchoServer3 {
        public static int DEFAULT_PORT = 7777;
 
        interface Handler {
                void execute(Selector selector, SelectionKey key);
        }
 
       
        public static void main(String[] args) throws IOException {
                System.out.println("Listening for connection on port " + DEFAULT_PORT);
 
                Selector selector = Selector.open();
                initServer(selector);
 
                while (true) {
                        selector.select();
 
                        for (Iterator<SelectionKey> itor = selector.selectedKeys().iterator(); itor.hasNext();) {
                                SelectionKey key = (SelectionKey) itor.next();
                                itor.remove();
                                Handler handler = (Handler) key.attachment();
                                handler.execute(selector, key);
                        }
                }
        }
 
        private static void initServer(Selector selector) throws IOException,
                        ClosedChannelException {
                ServerSocketChannel serverChannel = ServerSocketChannel.open();
                ServerSocket ss = serverChannel.socket();
                ss.bind(new InetSocketAddress(DEFAULT_PORT));
                serverChannel.configureBlocking(false);
                SelectionKey serverKey = serverChannel.register(selector, SelectionKey.OP_ACCEPT);
                serverKey.attach(new ServerHandler());
        }
       
        static class ServerHandler implements Handler {
                public void execute(Selector selector, SelectionKey key) {
                        ServerSocketChannel server = (ServerSocketChannel) key.channel();
                        SocketChannel client = null;
                        try {
                                client = server.accept();
                                System.out.println("Accepted connection from " + client);
                        } catch (IOException e) {
                                e.printStackTrace();
                                return;
                        }
                       
                        SelectionKey clientKey = null;
                        try {
                                client.configureBlocking(false);
                                clientKey = client.register(selector, SelectionKey.OP_READ);
                                clientKey.attach(new ClientHandler());
                        } catch (IOException e) {
                                if (clientKey != null)
                                        clientKey.cancel();
                                try { client.close(); } catch (IOException ioe) { }
                        }
                }
        }
       
        static class ClientHandler implements Handler {
                private ByteBuffer buffer;
               
                public ClientHandler() {
                        buffer = ByteBuffer.allocate(100);
                }
               
                public void execute(Selector selector, SelectionKey key) {
                        try {
                                if (key.isReadable()) {
                                        readKey(selector, key);
                                } else if (key.isWritable()) {
                                        writeKey(selector, key);
                                }
                        } catch (IOException e) {
                                key.cancel();
                                try { key.channel().close(); } catch (IOException ioe) { }
                        }
                }
               
                private void readKey(Selector selector, SelectionKey key) throws IOException {
                        SocketChannel client = (SocketChannel) key.channel();
                        int n = client.read(buffer);
                        if (n > 0) {
                                buffer.flip();
                                key.interestOps(SelectionKey.OP_WRITE);         // switch to OP_WRITE
                        }
                }
               
                private void writeKey(Selector selector, SelectionKey key) throws IOException {
                        // System.out.println("is writable...");
                        SocketChannel client = (SocketChannel) key.channel();
                        client.write(buffer);
                        if (buffer.remaining() == 0) {  // write finished, switch to OP_READ
                                buffer.clear();
                                key.interestOps(SelectionKey.OP_READ);
                        }
                }
        }
}