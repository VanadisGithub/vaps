package com.vanadis.vap.conf.security;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.oauth2.core.user.DefaultOAuth2User;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-22 11:56
 */
public class SecurityContext {

    public static DefaultOAuth2User getUser() {
        System.out.println(SecurityContextHolder.getContext().getAuthentication().getPrincipal());
        return (DefaultOAuth2User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
    }

}
