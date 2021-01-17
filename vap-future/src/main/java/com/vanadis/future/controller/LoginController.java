package com.vanadis.future.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * LoginController
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/1/7 9:16 下午
 */
@Controller
@RequestMapping()
public class LoginController {

    @RequestMapping("login")
    public String loginPage() throws InterruptedException {
        return "/login.html";
    }
}
