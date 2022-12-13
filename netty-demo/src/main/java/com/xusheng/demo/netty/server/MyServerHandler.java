package com.xusheng.demo.netty.server;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.channel.socket.SocketChannel;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

/**
 * @Author xusheng
 * @Date 2022/12/12 12:56
 * @Desc
 */
public class MyServerHandler extends ChannelInboundHandlerAdapter {

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        ChannelHandler.channelGroup.add(ctx.channel());
        SocketChannel channel = (SocketChannel) ctx.channel();
        System.out.println("链接报告信息：有一客户端链接到本服务端");
        System.out.println("链接报告IP:" + channel.localAddress().getHostString());
        System.out.println("链接报告Port:" + channel.localAddress().getPort());
        System.out.println("链接报告完毕");
        String msgRes = "连接成功" + channel.localAddress().getHostString() + "\r\n";
        ctx.writeAndFlush(msgRes);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        System.out.println("收到消息:" + msg);
        String msgRes = "服务端收到信息 " + msg + "\r\n";
        ChannelHandler.channelGroup.writeAndFlush(msgRes);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("客户端断开连接 " + ctx.channel().localAddress());
        ChannelHandler.channelGroup.remove(ctx.channel());
    }
}
