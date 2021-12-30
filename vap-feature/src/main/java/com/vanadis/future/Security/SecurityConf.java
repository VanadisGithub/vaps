package com.vanadis.future.Security;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-21 17:43
 */
@Slf4j
@Configuration
@EnableWebSecurity
public class SecurityConf extends WebSecurityConfigurerAdapter {

    private static final String[] WHITE_LIST = {"/**"};

    @Autowired
    private UserDetailServiceImpl userDetailService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
            .authorizeRequests()
            .antMatchers(WHITE_LIST).permitAll()
            .anyRequest().authenticated()
            .and().formLogin().loginPage("/login").loginProcessingUrl("/loginAction").permitAll()
            .successHandler(new SavedRequestAwareAuthenticationSuccessHandler())
            .and().logout().permitAll()
            .and().csrf().disable();
    }

    @Override
    protected void configure(AuthenticationManagerBuilder auth) throws Exception {
        auth
            .userDetailsService(userDetailService)
            .passwordEncoder(passwordEncoder());
    }

    /**
     * 加密逻辑，可以自定义
     *
     * @return
     */
    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * 认证管理bean
     *
     * @return
     * @throws Exception
     */
    @Bean
    public AuthenticationManager authManagerBean() throws Exception {
        return super.authenticationManagerBean();
    }

}
