package com.vanadis.air.service;

import com.alibaba.fastjson.JSON;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCollapser;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheRemove;
import com.netflix.hystrix.contrib.javanica.cache.annotation.CacheResult;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.concurrent.Future;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-06-17 15:10
 */
@Service
public class HystrixService {

    @Autowired
    RestTemplate restTemplate;

    public String test(String name) {
        return restTemplate.getForObject("http://vaps-vap/hystrix/test?context=" + name, String.class);
    }

    @HystrixCommand(commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
    })
    public String hystrix(String name) {
        return restTemplate.getForObject("http://vaps-vap/hystrix/hystrix?context=" + name, String.class);
    }

    @HystrixCommand(fallbackMethod = "hystrixFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
    })
    public String hystrix1(String name) {
        return restTemplate.getForObject("http://vaps-vap/hystrix/hystrix?context=" + name, String.class);
    }

    public String hystrixFallBack(String name) {

        //todo 可以在这里去做服务降级

        return "达也不在家" + name + "年了!";
    }

    @HystrixCommand(fallbackMethod = "hystrixFallBack", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "3000"),
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold", value = "10"),
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage", value = "50"),
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds", value = "5000")
    })
    public String hystrix2(String name) {
        return restTemplate.getForObject("http://vaps-vap/hystrix/hystrix?context=" + name, String.class);
    }

    @CacheResult
    @HystrixCommand(commandKey = "daye")
    public String hystrix3(String name) {
        return restTemplate.getForObject("http://vaps-vap/hystrix/hystrix?context=" + name, String.class) + System.currentTimeMillis();
    }

    @CacheRemove(commandKey = "daye")
    @HystrixCommand
    public String hystrix4(String name) {
        return "达也出去又回来了!";
    }

    @HystrixCollapser(batchMethod = "hystrixBatch", collapserProperties = {
            @HystrixProperty(name = "timerDelayInMilliseconds", value = "3000")
    })
    public Future<String> hystrix5(String name) {
        return null;
    }

    @HystrixCommand
    public List<String> hystrixBatch(List<String> names) {
        System.out.println(JSON.toJSONString(names));
        return restTemplate.getForObject("http://vaps-vap/hystrix/hystrix6?contexts={1}", List.class, StringUtils.join(names, ","));

    }

    @HystrixCommand(fallbackMethod = "hystrixFallBack",
            commandProperties = {
                    @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "300000"),
            },
            threadPoolProperties = {
                    @HystrixProperty(name = "coreSize", value = "2"),
                    @HystrixProperty(name = "maxQueueSize", value = "4")
            })
    public String hystrix6(String name) {
        return restTemplate.getForObject("http://vaps-vap/hystrix/hystrix?context=" + name, String.class);
    }


}
