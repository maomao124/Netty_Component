package mao.t2;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.string.StringDecoder;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t2
 * Class(类名): Server
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/18
 * Time(创建时间)： 13:57
 * Version(版本): 1.0
 * Description(描述)： NioEventLoop处理io事件
 */

@Slf4j
public class Server
{
    @SneakyThrows
    public static void main(String[] args)
    {
        new ServerBootstrap()
                //第一个参数是处理接收事件的EventLoop，线程数量为1个，第二个参数为处理读写事件的EventLoop，线程数量为3个
                .group(new NioEventLoopGroup(1), new NioEventLoopGroup(3))
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
                                        log.debug(ctx.toString());
                                        log.debug(msg);
                                    }
                                });
                    }
                })
                .bind(8080)
                .sync();

    }
}
