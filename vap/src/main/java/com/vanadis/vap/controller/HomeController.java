package com.vanadis.vap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
@Api(tags = "HomeController", description = "首页")
public class HomeController {

    @ApiOperation(value = "测试", notes = "测试备注")
    @ApiImplicitParam(name = "id", value = "123")
    @GetMapping("index")
    public String index(@RequestParam(required = false) String id) {
        return "hello world!";
    }
}
