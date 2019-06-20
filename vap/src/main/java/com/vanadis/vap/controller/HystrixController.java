package com.vanadis.vap.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @description:
 * @author: Created by 遥远 on 2019-06-16 15:24
 */
@RestController
@Api(tags = "TestController", description = "测试")
@RequestMapping("hystrix")
public class HystrixController {

    @ApiOperation(value = "测试")
    @GetMapping("test")
    public String test(@RequestParam(required = false) String context) {
        return "达也在家！";
    }

    @GetMapping("hystrix")
    public String hystrix(@RequestParam(required = false) String context) {
        if (context != null && !context.equals("null")) {
            Long time = Long.valueOf(context);
            try {
                Thread.sleep(time * 1000L);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        return "达也在家！";
    }

    @ApiImplicitParam(name = "context", value = "123")
    @GetMapping("hystrix5")
    public String hystrix5(@RequestParam(required = false) String context) {
        return "达也" + context + "号在家！";
    }

    @ApiImplicitParam(name = "context", value = "123")
    @GetMapping("hystrix6")
    public List<String> hystrix6(@RequestParam(required = false) List<String> contexts) {
        return contexts.stream().map(context -> "达也" + context + "号在家！").collect(Collectors.toList());
    }
}
