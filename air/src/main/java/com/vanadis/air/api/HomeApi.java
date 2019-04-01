package com.vanadis.air.api;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-26 19:09
 */
@Component
@FeignClient("vaps-cup")
public interface HomeApi {

    @GetMapping("/index")
    String index();
}
