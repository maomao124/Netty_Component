package mao.t3;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.EventLoop;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.util.concurrent.EventExecutor;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t3
 * Class(类名): Server
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/18
 * Time(创建时间)： 21:13
 * Version(版本): 1.0
 * Description(描述)： handler执行中更换工人
 */

@Slf4j
public class Server
{
    @SneakyThrows
    public static void main(String[] args)
    {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(3);
        new ServerBootstrap()
                //第一个参数是处理接收事件的EventLoop，线程数量为1个，第二个参数为处理读写事件的EventLoop，线程数量为3个
                .group(new NioEventLoopGroup(1), nioEventLoopGroup)
                .channel(NioServerSocketChannel.class)
                .childHandler(new ChannelInitializer<NioSocketChannel>()
                {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception
                    {
                        ch.pipeline().addLast(new StringDecoder())
                                .addLast(new SimpleChannelInboundHandler<String>()
                                {
                                    @Override
                                    protected void channelRead0(ChannelHandlerContext ctx, String msg) throws Exception
                                    {
                                        log.debug("当前处理的工人：" + Thread.currentThread().getName());
                                        EventLoop next = nioEventLoopGroup.next();
                                        //下一个 handler 的事件循环是否与当前的事件循环是同一个线程
                                        if (next.inEventLoop())
                                        {
                                            log.debug("更换工人，是同一个工人");
                                            log.debug(ctx.toString());
                                            log.debug(msg);
                                        }
                                        else
                                        {
                                            log.debug("更换工人，不是同一个工人");
                                            next.execute(new Runnable()
                                            {
                                                @Override
                                                public void run()
                                                {
                                                    log.debug("现在处理的工人：" + Thread.currentThread().getName());
                                                    log.debug(ctx.toString());
                                                    log.debug(msg);
                                                }
                                            });
                                        }
                                    }
                                });
                    }
                })
                .bind(8080)
                .sync();

    }
}
