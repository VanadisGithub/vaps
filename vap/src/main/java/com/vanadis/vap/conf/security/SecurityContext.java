package com.vanadis.vap.conf.security;

import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-22 11:56
 */
public class SecurityContext {

    public static User getUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
