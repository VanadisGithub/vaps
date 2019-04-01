package com.vanadis.cup.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-22 15:59
 */
@RestController
@Api(tags = "RedisController", description = "Redis")
@RequestMapping("redis")
public class RedisController {

    @Autowired
    private StringRedisTemplate template;

    @Autowired
    private CountDownLatch latch;

    @ApiOperation(value = "发布消息")
    @ApiImplicitParam(name = "test", value = "123")
    @GetMapping("sendMsg")
    public String sendMsg(@RequestParam(required = false) String test) {
        template.convertAndSend("test", "-Hello from Redis!");
        try {
            //发送消息连接等待中
            System.out.println("消息正在发送...");
            latch.await();
            System.out.println("消息发送成功...");

        } catch (InterruptedException e) {
            System.out.println("消息发送失败...");
        }
        return test;
    }

}
