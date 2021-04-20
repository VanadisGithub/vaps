package com.vanadis.auth;

import com.alibaba.fastjson.JSON;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.security.Principal;

@RestController
public class UserController {

    @RequestMapping("/user/me")
    public Principal user(Principal principal) {
        System.out.println(JSON.toJSON(principal));
        return principal;
    }

}
