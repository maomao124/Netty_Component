package mao.t1;

import io.netty.channel.DefaultEventLoopGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t1
 * Class(类名): EventLoopGroupTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/17
 * Time(创建时间)： 23:59
 * Version(版本): 1.0
 * Description(描述)： EventLoopGroup测试
 */

@Slf4j
public class EventLoopGroupTest
{
    @SneakyThrows
    public static void main(String[] args)
    {
        //内部创建了三个EventLoop,每个EventLoop维护一个线程
        DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup(3);
        log.debug(defaultEventLoopGroup.next().toString());
        log.debug(defaultEventLoopGroup.next().toString());
        log.debug(defaultEventLoopGroup.next().toString());
        //第4个和第一个地址一样
        log.debug(defaultEventLoopGroup.next().toString());
        defaultEventLoopGroup.submit(() -> log.debug("1"));
        Thread.sleep(100);
        defaultEventLoopGroup.submit(() -> log.debug("2"));
        Thread.sleep(100);
        defaultEventLoopGroup.submit(() -> log.debug("3"));
        Thread.sleep(100);
        //循环
        defaultEventLoopGroup.submit(() -> log.debug("4"));
        Thread.sleep(100);
        defaultEventLoopGroup.submit(() -> log.debug("5"));
        Thread.sleep(100);
        //和直接调用submit方法一样
        defaultEventLoopGroup.next().submit(() -> log.debug("6"));
    }
}
