package mao.t7;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import lombok.extern.slf4j.Slf4j;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t7
 * Class(类名): HandlerTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/20
 * Time(创建时间)： 19:45
 * Version(版本): 1.0
 * Description(描述)： Handler
 */

@Slf4j
public class HandlerTest
{
    public static void main(String[] args)
    {
        new ServerBootstrap()
                .group(new NioEventLoopGroup(), new NioEventLoopGroup(3))
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>()
                {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception
                    {
                        ch.pipeline().addLast(new ChannelInboundHandlerAdapter()
                                {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
                                    {
                                        log.debug("入栈处理器：1");
                                        super.channelRead(ctx, msg);
                                    }
                                })
                                .addLast(new ChannelInboundHandlerAdapter()
                                {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
                                    {
                                        log.debug("入栈处理器：2");
                                        super.channelRead(ctx, msg);
                                    }
                                })
                                .addLast(new ChannelInboundHandlerAdapter()
                                {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
                                    {
                                        log.debug("入栈处理器：3");
                                        super.channelRead(ctx, msg);
                                    }
                                })
                                .addLast(new ChannelInboundHandlerAdapter()
                                {
                                    @Override
                                    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception
                                    {
                                        log.debug("入栈处理器：4");
                                        ctx.channel().write("hello");
                                    }
                                })
                                .addLast(new ChannelOutboundHandlerAdapter()
                                {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
                                            throws Exception
                                    {
                                        log.debug("出栈处理器：5");
                                        super.write(ctx, msg, promise);
                                    }
                                })
                                .addLast(new ChannelOutboundHandlerAdapter()
                                {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
                                            throws Exception
                                    {
                                        log.debug("出栈处理器：6");
                                        super.write(ctx, msg, promise);
                                    }
                                })
                                .addLast(new ChannelOutboundHandlerAdapter()
                                {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
                                            throws Exception
                                    {
                                        log.debug("出栈处理器：7");
                                        super.write(ctx, msg, promise);
                                    }
                                })
                                .addLast(new ChannelOutboundHandlerAdapter()
                                {
                                    @Override
                                    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise)
                                            throws Exception
                                    {
                                        log.debug("出栈处理器：8");
                                        super.write(ctx, msg, promise);
                                    }
                                });
                    }
                })
                .bind(8080);
    }
}
