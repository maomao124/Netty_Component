package mao.t5;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;
import java.util.Scanner;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t5
 * Class(类名): Client3
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/18
 * Time(创建时间)： 22:09
 * Version(版本): 1.0
 * Description(描述)： CloseFuture
 */

@Slf4j
public class Client3
{
    @SneakyThrows
    public static void main(String[] args)
    {
        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup();
        //获得channelFuture对象
        ChannelFuture channelFuture = new Bootstrap()
                .channel(NioSocketChannel.class)
                .group(nioEventLoopGroup)
                .handler(new ChannelInitializer<NioSocketChannel>()
                {
                    @Override
                    protected void initChannel(NioSocketChannel ch) throws Exception
                    {
                        ch.pipeline().addLast(new StringEncoder());
                    }
                })
                .connect(new InetSocketAddress(8080));

        log.debug("------------");
        log.debug(channelFuture.toString());
        channelFuture.sync();
        Channel channel = channelFuture.channel();

        Scanner input = new Scanner(System.in);

        new Thread(new Runnable()
        {
            @Override
            public void run()
            {
                while (true)
                {
                    String s = input.next();
                    if ("q".equals(s))
                    {
                        log.info("即将退出");
                        //调用close方法是异步操作，不能在close方法之后写关闭的业务逻辑
                        channel.close();
                    }
                    else
                    {
                        log.debug("即将发送的字符串：" + s);
                        channel.writeAndFlush(s);
                    }
                }
            }
        }, "input").start();

        //获得closeFuture
        ChannelFuture closeFuture = channel.closeFuture();
        closeFuture.addListener(new ChannelFutureListener()
        {
            /**
             * 操作完成(关闭)
             *
             * @param future ChannelFuture
             * @throws Exception 异常
             */
            @Override
            public void operationComplete(ChannelFuture future) throws Exception
            {
                log.debug("处理关闭之后的操作");
                nioEventLoopGroup.shutdownGracefully();
                log.info("关闭完成");
            }
        });

    }
}
