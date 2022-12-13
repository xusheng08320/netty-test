package com.xusheng.demo.netty.server;

import io.netty.channel.ChannelInitializer;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LineBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;

import java.nio.charset.Charset;

/**
 * @Author xusheng
 * @Date 2022/12/12 12:54
 * @Desc
 */
public class MyChannelInitializer extends ChannelInitializer<SocketChannel> {
    @Override
    protected void initChannel(SocketChannel channel) throws Exception {
        channel.pipeline()
                .addLast(new LineBasedFrameDecoder(1024))
                .addLast(new StringDecoder(Charset.forName("GBK")))
                .addLast(new StringEncoder(Charset.forName("GBK")))
                .addLast(new MyServerHandler());
    }
}
