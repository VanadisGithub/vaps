package com.vanadis.vap.controller.test;

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

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
@Api(tags = "TestController", description = "测试")
@RequestMapping("test")
public class TestController {

    @Autowired
    private JavaMailSender mailSender;

    @Value("${spring.mail.username}")
    private String sender;

    @ApiOperation(value = "发送邮件")
    @ApiImplicitParam(name = "context", value = "123")
    @GetMapping("sendEmail")
    public String sendEmail(@RequestParam(required = false) String context) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(sender);
        message.setTo("872671438@qq.com");
        message.setSubject("主题：简单邮件");
        message.setText(context);
        mailSender.send(message);
        return context;
    }

}
