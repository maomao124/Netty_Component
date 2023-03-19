package mao.t6;

import io.netty.channel.DefaultEventLoopGroup;
import io.netty.util.concurrent.Future;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.Callable;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t6
 * Class(类名): FutureTest
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/19
 * Time(创建时间)： 22:24
 * Version(版本): 1.0
 * Description(描述)： Future测试，同步处理任务
 */

@Slf4j
public class FutureTest
{
    @SneakyThrows
    public static void main(String[] args)
    {
        DefaultEventLoopGroup defaultEventLoopGroup = new DefaultEventLoopGroup(3);
        Future<Integer> future = defaultEventLoopGroup.submit(new Callable<Integer>()
        {
            @Override
            public Integer call() throws Exception
            {
                try
                {
                    Thread.sleep(1000);
                }
                catch (InterruptedException e)
                {
                    e.printStackTrace();
                }
                log.debug("执行成功");
                return 10;
            }
        });

        log.debug(future.toString());
        //现在读取，无法读取
        Integer integer = future.getNow();
        log.debug(String.valueOf(integer));
        log.debug("开始同步等待结果");
        future.sync();
        integer = future.getNow();
        log.debug(integer.toString());
    }
}
