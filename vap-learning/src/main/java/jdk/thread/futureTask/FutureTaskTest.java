package jdk.thread.futureTask;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.FutureTask;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * FutureTaskTest
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/1/14 11:34 上午
 */
public class FutureTaskTest {

    private static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(2, 2,
        60L, TimeUnit.SECONDS,
        new SynchronousQueue<Runnable>());

    public static void main(String[] args) {
        UpgradeTask upgradeTask = () -> System.out.println("任务处理");
        List<FutureTask<Boolean>> taskList = new LinkedList<>();
        FutureTask<Boolean> submitTask = new FutureTask<>(() -> {
            upgradeTask.run();
            return Boolean.TRUE;
        });
        taskList.add(submitTask);
        threadPoolExecutor.submit(submitTask);
    }

    public interface UpgradeTask {
        void run();
    }
}
