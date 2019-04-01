package com.vanadis.cup.conf.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-21 17:43
 */

@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
//        http.authorizeRequests().anyRequest().authenticated();
        http.formLogin();
        http.csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth.userDetailsService(userDetailService).passwordEncoder(new PasswordEncoder() {
            @Override
            public String encode(CharSequence charSequence) {
                return charSequence.toString();
            }

            @Override
            public boolean matches(CharSequence charSequence, String s) {
                return s.equals(charSequence.toString());
            }
        });
    }

}
