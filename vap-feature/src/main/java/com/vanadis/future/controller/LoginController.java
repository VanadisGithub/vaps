package com.vanadis.future.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController
 *
 * @author yaoyuan
 * @date 2021/1/7 9:16 下午
 */
@Controller
@RequestMapping()
public class LoginController {

    @GetMapping("login")
    public String loginPage() throws InterruptedException {
        return "/login.html";
    }
}
