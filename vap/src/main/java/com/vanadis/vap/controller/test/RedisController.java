//package com.vanadis.vap.controller.test;
//
//import com.google.common.collect.Lists;
//import io.swagger.annotations.Api;
//import io.swagger.annotations.ApiImplicitParam;
//import io.swagger.annotations.ApiOperation;
//import jodd.util.ThreadUtil;
//import lombok.extern.slf4j.Slf4j;
//import org.redisson.api.RLock;
//import org.redisson.api.RedissonClient;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.cache.annotation.Cacheable;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.web.bind.annotation.GetMapping;
//import org.springframework.web.bind.annotation.RequestMapping;
//import org.springframework.web.bind.annotation.RequestParam;
//import org.springframework.web.bind.annotation.RestController;
//
//import java.util.List;
//import java.util.Random;
//import java.util.concurrent.CountDownLatch;
//
///**
// * @description:
// * @author: Created by 遥远 on 2019-03-22 15:59
// */
//@Slf4j
//@Api("redis")
//@RestController
//@RequestMapping("redis")
//public class RedisController {
//
//    @Autowired
//    private RedissonClient redissonClient;
//
//    /**
//     * 分布式锁
//     */
//    @GetMapping("lock")
//    public void lock() {
//        log.info("进入方法");
//        String test = "dev_test";
//        RLock lock = redissonClient.getLock(test);
//        if (lock.tryLock()) {
//            try {
//                log.info("加锁");
//                ThreadUtil.sleep(5000L);
//            } finally {
//                lock.unlock();
//                log.info("解锁");
//            }
//        } else {
//            log.info("结束");
//        }
//    }
//
//    @Autowired
//    private StringRedisTemplate template;
//
//    @Autowired
//    private CountDownLatch latch;
//
//    /**
//     * 消息订阅
//     *
//     * @param test
//     * @return
//     */
//    @ApiOperation(value = "发布消息")
//    @ApiImplicitParam(name = "test", value = "123")
//    @GetMapping("sendMsg")
//    public String sendMsg(@RequestParam(required = false) String test) {
//        template.convertAndSend("theme", "Hello! I'm Redis!");
//        try {
//            //发送消息连接等待中
//            log.info("[vap.redis.发布]正在发送...");
//            latch.await();
//            log.info("[vap.redis.发布]发送成功...");
//
//        } catch (InterruptedException e) {
//            log.info("[vap.redis.发布]发送失败...");
//        }
//        return test;
//    }
//
//    /**
//     * 缓存
//     *
//     * @param test
//     * @return
//     */
//    @Cacheable(value = "cache")
//    @ApiOperation(value = "缓存", notes = "测试备注")
//    @ApiImplicitParam(name = "test", value = "123")
//    @GetMapping("cacheAble")
//    public String cacheAble(@RequestParam(required = false) String test) {
//        return String.valueOf(new Random().nextInt(100));
//    }
//
//    /**
//     * 缓存list
//     *
//     * @param test
//     * @return
//     */
//    @Cacheable(value = "cache")
//    @ApiOperation(value = "缓存List", notes = "测试备注")
//    @ApiImplicitParam(name = "test", value = "123")
//    @GetMapping("cacheAbleList")
//    public List<Integer> cacheAbleList(@RequestParam(required = false) String test) {
//        List<Integer> list = Lists.newArrayList();
//        list.add(new Random().nextInt(100));
//        list.add(new Random().nextInt(100));
//        list.add(new Random().nextInt(100));
//        return list;
//    }
//
//}
