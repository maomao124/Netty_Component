package mao.t2;

import io.netty.bootstrap.Bootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.net.InetSocketAddress;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t2
 * Class(类名): Client
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/18
 * Time(创建时间)： 14:07
 * Version(版本): 1.0
 * Description(描述)： NioEventLoop处理io事件
 */

@Slf4j
public class Client
{
    @SneakyThrows
    public static void main(String[] args)
    {

        for (int i = 0; i < 5; i++)
        {
            Channel channel = new Bootstrap()
                    .group(new NioEventLoopGroup(1))
                    .channel(NioSocketChannel.class)
                    .handler(new ChannelInitializer<NioSocketChannel>()
                    {
                        @Override
                        protected void initChannel(NioSocketChannel ch) throws Exception
                        {
                            ch.pipeline().addLast(new StringEncoder())
                                    .addLast(new LoggingHandler(LogLevel.DEBUG));
                        }
                    })
                    .connect(new InetSocketAddress(8080)).sync().channel();

            channel.writeAndFlush("客户端" + (i + 1) + " -1");
            Thread.sleep(100);
            channel.writeAndFlush("客户端" + (i + 1) + " -2");
            Thread.sleep(100);
            channel.writeAndFlush("客户端" + (i + 1) + " -3");
            Thread.sleep(100);
            channel.writeAndFlush("客户端" + (i + 1) + " -4");
            Thread.sleep(100);
            channel.writeAndFlush("客户端" + (i + 1) + " -5");
            Thread.sleep(100);
            channel.close();
        }

    }
}
