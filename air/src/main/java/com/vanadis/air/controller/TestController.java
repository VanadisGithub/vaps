package com.vanadis.air.controller;

import com.vanadis.air.service.TestService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
@Api(tags = "TestController", description = "测试")
public class TestController {

    @Autowired
    private TestService testService;

    @ApiOperation(value = "测试服务通否")
    @ApiImplicitParam(name = "txt", value = "123")
    @GetMapping("test")
    public String test(@RequestParam(required = false) String id) {
        return testService.index();
    }

    @ApiOperation(value = "服务降级")
    @ApiImplicitParam(name = "txt", value = "123")
    @GetMapping("hystrix")
    public String hystrix(@RequestParam(required = false) String id) {
        return testService.hystrix();
    }

}
