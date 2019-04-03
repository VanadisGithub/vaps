package com.vanadis.air.service;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-26 19:09
 */
@Service
@FeignClient("vaps-cup")
public interface TestService {

    @GetMapping("/test/test")
    String index();

    @GetMapping("/test/hystrix")
    String hystrix();

}
