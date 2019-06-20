package com.vanadis.air.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-26 19:09
 */
@Service
@FeignClient(value = "vaps-vap", fallback = TestService.class)//,
public interface TestService {

    @GetMapping("/hystrix/test")
    String test();

    @GetMapping("/hystrix/hystrix")
    String hystrix();

    @GetMapping("/hystrix/hystrix")
    String hystrix2();

    default String hystrixFallback() {
        return "达也不在家！";
    }

}
