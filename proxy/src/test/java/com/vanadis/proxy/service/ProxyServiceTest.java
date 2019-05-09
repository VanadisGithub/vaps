package com.vanadis.proxy.service;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import org.junit.Test;

import java.util.concurrent.*;

import static org.junit.Assert.*;

/**
 * @description:
 * @author: Created by 遥远 on 2019-05-09 22:26
 */
public class ProxyServiceTest {

    @Test
    public void getProxyOfXici() {
        ThreadFactory namedThreadFactory = new ThreadFactoryBuilder().setNameFormat("demo-pool-%d").build();
        ExecutorService singleThreadPool = new ThreadPoolExecutor(2, 10,
                10L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(10), namedThreadFactory, new ThreadPoolExecutor.AbortPolicy());
        String[] name = {"nt", "nn", "wt", "wn"};
        for (int index = 0; index < name.length; index++) {
            for (int page = 1; page <= 3; page++) {
                singleThreadPool.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                });
            }
        }
        singleThreadPool.shutdown();
    }
}