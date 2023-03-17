package mao.t1;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.util.concurrent.EventExecutor;
import lombok.extern.slf4j.Slf4j;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t1
 * Class(类名): EventLoopGroupTest2
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/18
 * Time(创建时间)： 0:08
 * Version(版本): 1.0
 * Description(描述)： EventLoopGroup测试
 */

@Slf4j
public class EventLoopGroupTest2
{
    public static void main(String[] args)
    {
        //内部创建了三个EventLoop,每个EventLoop维护一个线程
        DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup(3);
        for (EventExecutor eventExecutor : defaultEventLoopGroup)
        {
            log.debug(eventExecutor.toString());
        }
        //再次调用
        for (EventExecutor eventExecutor : defaultEventLoopGroup)
        {
            log.debug(eventExecutor.toString());
        }
    }
}
