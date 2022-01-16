package com.vanadis.future.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:24
 */
@RestController
public class HomeController {

    private static final String LOGOUT = "<a href=\"/logout\"><h1>登出</h1></a>";

    @GetMapping(path = {"/", "/home"})
    public String home() {
        return "<a href=\"/page\"><h1>page</h1></a>" + LOGOUT;
    }

    @GetMapping("page")
    public String page() {
        return "<a href=\"/home\"><h1>home</h1></a>" + LOGOUT;
    }

}



