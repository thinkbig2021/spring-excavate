package com.yumeng.spring.Reactor2.mycopy;

import java.io.Serializable;
import java.nio.channels.SocketChannel;

/**
 * Created by yumeng on 2017/5/4.
 */
public class NioTask implements Serializable {

    private SocketChannel socketChannel;
    private int ops;
    private Object data;
    public NioTask(SocketChannel socketChannel, int ops, Object data) {
        this.socketChannel = socketChannel;
        this.ops = ops;
        this.data = data;

    }
    public SocketChannel getSocketChannel() {
        return socketChannel;
    }

    public void setSocketChannel(SocketChannel socketChannel) {
        this.socketChannel = socketChannel;
    }

    public int getOps() {
        return ops;
    }

    public void setOps(int ops) {
        this.ops = ops;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }



}
