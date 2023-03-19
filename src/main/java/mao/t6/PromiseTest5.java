package mao.t6;

import io.netty.channel.DefaultEventLoop;
import io.netty.util.concurrent.DefaultPromise;
import io.netty.util.concurrent.Promise;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;

/**
 * Project name(项目名称)：Netty_Component
 * Package(包名): mao.t6
 * Class(类名): PromiseTest5
 * Author(作者）: mao
 * Author QQ：1296193245
 * GitHub：https://github.com/maomao124/
 * Date(创建日期)： 2023/3/19
 * Time(创建时间)： 23:03
 * Version(版本): 1.0
 * Description(描述)： 同步处理任务失败 await
 */

@Slf4j
public class PromiseTest5
{
    @SneakyThrows
    public static void main(String[] args)
    {
        DefaultEventLoop defaultEventLoop = new DefaultEventLoop();
        Promise<Integer> promise = new DefaultPromise<>(defaultEventLoop);
        defaultEventLoop.execute(new Runnable()
        {
            @Override
            public void run()
            {
                try
                {
                    Thread.sleep(1000);

                    if (System.currentTimeMillis() % 2 == 0)
                    {
                        throw new RuntimeException("执行错误");
                    }
                    log.debug("执行完成");
                    promise.setSuccess(100);
                }
                catch (Exception e)
                {
                    log.debug("执行错误");
                    promise.setFailure(e);
                }
            }
        });

        log.debug(promise.toString());
        log.debug("读取数据：" + promise.getNow());
        log.debug("同步等待结果");
        promise.await();
        boolean success = promise.isSuccess();
        if (success)
        {
            log.debug("结果：" + promise.getNow());
        }
        else
        {
            Throwable throwable = promise.cause();
            log.warn("处理失败", throwable);
        }

    }
}
