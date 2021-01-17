package com.vanadis.vap.controller;

import com.alibaba.fastjson.JSON;

import com.vanadis.vap.common.VapAnnotation;
import com.vanadis.vap.conf.security.SecurityContext;
import com.vanadis.vap.conf.security.User;
import com.vanadis.vap.mapper.BackUserMapper;
import com.vanadis.vap.object.TestModel;
import com.vanadis.vap.service.UserService;
import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.oauth2.client.authentication.OAuth2AuthenticationToken;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@Api("首页")
@RestController
public class HomeController {

    @Autowired
    private UserService userService;

    @Autowired
    private BackUserMapper backUserMapper;

    @GetMapping("index")
    public User index() throws InterruptedException {
        return SecurityContext.getUser();
    }

    @PreAuthorize("hasAuthority('abc')")
    @GetMapping("test")
    public void test() throws InterruptedException {
        User user = new User("123", "123");
        userService.save(user);
        Thread.sleep(1000L);
        user.setUsername("456");
        userService.saveOrUpdate(user);
        userService.removeById(user);
    }

    @VapAnnotation
    @GetMapping("annotation")
    public TestModel annotation(OAuth2AuthenticationToken authentication) {
        System.out.println(JSON.toJSON(authentication));
        return new TestModel();
    }

    @GetMapping("login/oauth2/code/vap")
    public String rallback() {
        return "123";
    }

}



