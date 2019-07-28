package generator.tool.factory;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

/**
 * <p>Title: </p>
 * <p>Description:</p>
 *
 * @author <a href="mailTo:bingxuewulei@outlook.com">wanglei</a>
 * @version 1.0
 * @history: Created by wanglei on  2019/7/20
 */
public class FeatureService <T> {
    private ExecutorService executor;
    public FeatureService(int threadCount){
        // 创建Executor- Service，通 过它你可以 向线程池提 交任务
         executor =   Executors.newFixedThreadPool(threadCount); //使用线程池
    }

    // 向Executor- Service提交一个 Callable对象
    public  Future <T> submit (Callable tCallable){
         return executor.submit(tCallable);
    }
}

