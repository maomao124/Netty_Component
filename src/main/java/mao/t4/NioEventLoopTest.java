package mao.t4;

import io.netty.channel.nio.NioEventLoopGroup;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t4
 * Class(类名): NioEventLoopTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/18
 * Time(创建时间)： 21:33
 * Version(版本): 1.0
 * Description(描述)： NioEventLoop处理普通任务
 */

@Slf4j
public class NioEventLoopTest
{
    @SneakyThrows
    public static void main(String[] args)
    {

        NioEventLoopGroup nioEventLoopGroup = new NioEventLoopGroup(3);
        log.debug("启动");
        for (int i = 0; i < 10; i++)
        {
            nioEventLoopGroup.execute(new Runnable()
            {
                @Override
                public void run()
                {
                    log.debug("当前线程：" + Thread.currentThread().getName());
                }
            });
            Thread.sleep(100);
        }
    }
}
