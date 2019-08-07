package com.vanadis.vap.conf.security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-07 15:10
 */
@Slf4j
public class UsernamePasswordAuthJsonFilter extends UsernamePasswordAuthenticationFilter {

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
        log.info("密码校验");
        if (System.currentTimeMillis() != 0) {
            throw new AuthenticationServiceException("GG");
        } else {
            UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(
                    "123", "123");
            Authentication authentication = this.getAuthenticationManager().authenticate(authRequest);
            return authentication;
        }
    }
}
