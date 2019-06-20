package com.vanadis.air.controller;

import com.vanadis.air.service.HystrixService;
import com.vanadis.air.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.LinkedList;
import java.util.List;
import java.util.concurrent.*;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
@Api(tags = "TestController", description = "测试")
public class TestController {

    @Autowired
    private TestService testService;

    @Autowired
    private HystrixService hystrixService;

    @ApiOperation(value = "测试服务是否通畅")
    @GetMapping("test")
    public String test(@RequestParam(required = false) String id) {
        return hystrixService.test(id);
    }

    @ApiOperation(value = "服务超时")
    @GetMapping("hystrix")
    public String hystrix(@RequestParam(required = false) String t) {
        return hystrixService.hystrix(t);
    }

    @ApiOperation(value = "服务降级")
    @GetMapping("hystrix1")
    public String hystrix1(@RequestParam(required = false) String t) {
        return hystrixService.hystrix1(t);
    }

    @ApiOperation(value = "服务熔断")
    @GetMapping("hystrix2")
    public String hystrix2(@RequestParam(required = false) String t) throws InterruptedException {
        System.out.println("\n");
        System.out.println("--------------");
        StringBuffer buffer = new StringBuffer();
        Executor threadPool = Executors.newFixedThreadPool(10);
        for (int i = 1; i <= 10; i++) {
            int finalI = i;
            threadPool.execute(() -> System.out.println(hystrixService.hystrix2(finalI + "")));
        }
        return buffer.toString();
    }

    @ApiOperation(value = "服务缓存")
    @GetMapping("hystrix3")
    public String hystrix3(@RequestParam(required = false) String t) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            buffer.append(hystrixService.hystrix3(t));
            buffer.append("<br>");
        }
        return buffer.toString();
    }

    @ApiOperation(value = "服务缓存（刷新）")
    @GetMapping("hystrix4")
    public String hystrix4(@RequestParam(required = false) String t) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 3; i++) {
            buffer.append(hystrixService.hystrix3(t));
            buffer.append("<br>");
        }
        buffer.append(hystrixService.hystrix4(t));
        buffer.append("<br>");
        for (int i = 0; i < 3; i++) {
            buffer.append(hystrixService.hystrix3(t));
            buffer.append("<br>");
        }
        return buffer.toString();
    }

    @ApiOperation(value = "服务合并")
    @GetMapping("hystrix5")
    public String hystrix5(@RequestParam(required = false) String t) throws
            ExecutionException, InterruptedException {

        StringBuffer buffer = new StringBuffer();

        Future<String> task1 = hystrixService.hystrix5(1 + "");
        Future<String> task2 = hystrixService.hystrix5(2 + "");
        Future<String> task3 = hystrixService.hystrix5(3 + "");

        buffer.append(task1.get());
        buffer.append("<br>");
        buffer.append(task2.get());
        buffer.append("<br>");
        buffer.append(task3.get());
        buffer.append("<br>");

        return buffer.toString();
    }

    @ApiOperation(value = "服务限流")
    @GetMapping("hystrix6")
    public String hystrix6(@RequestParam(required = false) String t) {
        StringBuffer buffer = new StringBuffer();
        for (int i = 0; i < 10; i++) {
            new Thread(() -> System.out.println(hystrixService.hystrix6(t))).start();
        }
        return buffer.toString();
    }


}
