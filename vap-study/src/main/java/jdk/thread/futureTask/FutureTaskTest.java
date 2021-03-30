package jdk.thread.futureTask;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

/**
 * FutureTaskTest
 *
 * @author yaoyuan
 * @date 2021/1/14 11:34 上午
 */
public class FutureTaskTest {

    private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(8, 16,
        10, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(16),
        new ThreadFactoryBuilder().setNameFormat("Thread-%d").build(),
        new ThreadPoolExecutor.AbortPolicy());

    public static void main(String[] args) throws ExecutionException, InterruptedException {
        List<FutureTask<Boolean>> taskList = new LinkedList<>();
        for (int i = 0; i < 10; i++) {
            int finalI = i;
            FutureTask<Boolean> submitTask = new FutureTask<>(() -> {
                //任务处理
                System.out.println(finalI);
                return Boolean.TRUE;
            });
            taskList.add(submitTask);
            threadPoolExecutor.submit(submitTask);
        }
        for (FutureTask<Boolean> futureTask : taskList) {
            futureTask.get();
        }
    }
}
