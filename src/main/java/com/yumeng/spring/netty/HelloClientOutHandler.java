package com.yumeng.spring.netty;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelOutboundHandlerAdapter;
import io.netty.channel.ChannelPromise;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by yumeng on 2017/4/26.
 */
public class HelloClientOutHandler extends ChannelOutboundHandlerAdapter {

    private static Log logger	= LogFactory.getLog(OutboundHandler1.class);
    @Override
    //像服务端发送信息
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        logger.info("HelloClientOutHandler.write");
        String response = "haha，I am come form client!";
        ByteBuf encoded = ctx.alloc().buffer(4 * response.length());
        encoded.writeBytes(response.getBytes());
        ctx.write(encoded);
        ctx.flush();
    }
}
