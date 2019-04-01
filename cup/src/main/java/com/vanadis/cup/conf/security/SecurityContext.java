package com.vanadis.cup.conf.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-22 11:56
 */
public class SecurityContext {

    public static User getUser() {
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }
}
