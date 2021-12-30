package com.vanadis.future.controller;

import java.util.List;
import java.util.Set;
import java.util.concurrent.BlockingQueue;
import java.util.concurrent.FutureTask;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.alibaba.fastjson.JSONObject;

import com.google.common.collect.Lists;
import com.google.common.collect.Sets;
import com.google.common.util.concurrent.ThreadFactoryBuilder;
import com.vanadis.future.hook.SpringContextHolder;
import com.vanadis.future.hook.StageResult;
import com.vanadis.future.hook.StatusHook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
public class HomeController {

    public static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16,
        10, TimeUnit.SECONDS,
        new LinkedBlockingQueue<>(100),
        new ThreadFactoryBuilder().setNameFormat("Test-%d").build(),
        new ThreadPoolExecutor.AbortPolicy());

    private static final Logger log = LoggerFactory.getLogger(HomeController.class);

    @Autowired
    private SpringContextHolder springContextHolder;

    //@Autowired
    //private List<StatusHook> statusHooks;
    private Set<String> idSet = Sets.newHashSet();

    @GetMapping(path = {"/", "/home"})
    public String home() throws InterruptedException {
        //RestTemplate restTemplate = new RestTemplate();
        //String forObject = restTemplate.getForObject("http://127.0.0.1:8080/page", String.class);
        //System.out.println(forObject);
        return "<a href=\"/page\"><h1>Hello!</h1></a>";
    }

    @GetMapping("page")
    public String page() throws InterruptedException {
        return "<h1>Page!</h1>";
    }

    @GetMapping("hook")
    public String hook() throws ClassNotFoundException {
        StatusHook successHook = springContextHolder.getTask("successHook");
        StatusHook errorHook = springContextHolder.getTask("errorHook");
        List<StatusHook> list = Lists.newArrayList(successHook, errorHook);
        for (StatusHook statusHook : list) {
            statusHook.print("test");
        }
        Class<?> aClass = Class.forName("com.vanadis.future.hook.StageResult");
        StageResult o = (StageResult<Integer>)JSONObject.parseObject("{\n"
            + "\"result\":\"sss\"\n"
            + "}", aClass);
        return "test";
    }

    @GetMapping("threadPool")
    public String threadPool() throws ClassNotFoundException, IllegalAccessException {
        for (int i = 0; i < 100; i++) {
            if (i == 50) {
                threadPoolExecutor.shutdown();
                System.out.println(idSet);

                BlockingQueue<Runnable> queue = threadPoolExecutor.getQueue();
                for (Runnable runnable : queue) {
                    FutureTask<String> task = (FutureTask<String>)runnable;

                    //Class clazz = Class.forName("com.vanadis.future.controller.HomeController.NewRunnable");
                    //Field[] fs = clazz.getDeclaredFields();
                    //for (Field field : fs) {
                    //    /**
                    //     如果成员变量是private的,则需要设置accessible为true
                    //     如果成员变量是public的,则不需要设置
                    //     **/
                    //    field.setAccessible(true);
                    //    System.out.println(field.get(task));
                    //}
                    //System.out.print(((NewRunnable)((RunnableAdapter)task.callable).task).i);
                }
            }
            threadPoolExecutor.submit(new NewRunnable(i));
        }
        return "1";
    }

    public class NewRunnable implements Runnable {

        public Integer i;

        public NewRunnable(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {

            log.info(i + " start");
            idSet.add(i.toString());

            try {
                Thread.sleep(2 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            idSet.remove(i.toString());

        }

        public Integer getI() {
            return i;
        }
    }

    public class NewThread extends Thread {

        public Integer i;

        public NewThread(Integer i) {
            this.i = i;
        }

        @Override
        public void run() {
            log.info(i + " start");
            try {
                Thread.sleep(3 * 1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            //if (i % 10 == 0) {
            //    threadPoolExecutor.submit(new NewThread(i * 10));
            //}
            log.info(i + " end");
        }

        public Integer getI() {
            return i;
        }
    }

}



