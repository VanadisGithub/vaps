package jdk.thread;

import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadFactory;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.ThreadPoolExecutor.CallerRunsPolicy;
import java.util.concurrent.TimeUnit;

import com.google.common.collect.Lists;
import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * CallableTest
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/10/30 3:53 下午
 */
public class CallableTest {

    private ThreadFactory threadFactory = (new ThreadFactoryBuilder()).setNameFormat("submit-objects-thread-%d")
        .build();
    private ThreadPoolExecutor executorService = new ThreadPoolExecutor(10, 10, 1, TimeUnit.MINUTES,
        new LinkedBlockingQueue(10), threadFactory, new CallerRunsPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        int sum = 0;
        List<Future<Object>> rs = Lists.newArrayList();
        for (int i = 0; i < 10; i++) {
            sum += i;
            rs.add(new CallableTest().executorService.submit(new Sum(i, i)));
        }
        System.out.println(sum);
        sum = 0;
        for (Future<Object> r : rs) {
            sum += (Integer)r.get();
        }
        System.out.println(sum);
    }
}
