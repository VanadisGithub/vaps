package com.vanadis.lang.thread;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.Callable;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * FetureTask
 *
 * @author yaoyuan
 * @date 2021/1/26 8:34 下午
 */
public class FutureTaskTemplate<T> {

    private static final Logger log = LoggerFactory.getLogger(FutureTaskTemplate.class);

    private ThreadPoolExecutor threadPoolExecutor;
    private List<FutureTask<T>> taskList;

    public FutureTaskTemplate() {
        this.taskList = new LinkedList<>();
        this.threadPoolExecutor = new ThreadPoolExecutor(8, 16,
            10, TimeUnit.SECONDS,
            new LinkedBlockingQueue<>(16),
            new ThreadFactoryBuilder().setNameFormat("Thread-%d").build(),
            new ThreadPoolExecutor.AbortPolicy());
    }

    public FutureTaskTemplate(ThreadPoolExecutor threadPoolExecutor) {
        this.taskList = new LinkedList<>();
        this.threadPoolExecutor = threadPoolExecutor;
    }

    public static void main(String[] args) {
        FutureTaskTemplate<Boolean> taskTemplate = new FutureTaskTemplate<>();
        for (int i = 1; i < 11; i++) {
            int finalI = i;
            taskTemplate.addTask(() -> {
                if (finalI > 8) {
                    throw new Exception("1");
                }
                return finalI * finalI > 40;
            });
        }
        try {
            taskTemplate.results().forEach(System.out::println);
        } catch (ExecutionException | InterruptedException e) {
            log.error(e.getMessage(), e);
        }
    }

    private void addTask(Callable<T> task) {
        FutureTask<T> futureTask = new FutureTask<>(task);
        taskList.add(futureTask);
        threadPoolExecutor.submit(futureTask);
    }

    private List<T> results() throws ExecutionException, InterruptedException {
        List<T> results = new LinkedList<>();
        for (FutureTask<T> futureTask : taskList) {
            results.add(futureTask.get());
        }
        return results;
    }
}
