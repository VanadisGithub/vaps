package com.vanadis.future.Security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.web.http.CookieHttpSessionIdResolver;
import org.springframework.session.web.http.CookieSerializer;
import org.springframework.session.web.http.DefaultCookieSerializer;
import org.springframework.session.web.http.HttpSessionIdResolver;

/**
 * SpringSessionBeanConfiguration
 *
 * @author yaoyuan
 * @date 2020/12/29 3:12 下午
 */
@Configuration
public class SpringSessionBeanConfiguration {

    //@Bean
    //public CookieSerializer cookieSerializer() {
    //    DefaultCookieSerializer cookieSerializer = new DefaultCookieSerializer();
    //    cookieSerializer.setCookieName("VAN");
    //    cookieSerializer.setCookieMaxAge(300);
    //    cookieSerializer.setCookiePath("/");
    //    cookieSerializer.setDomainNamePattern("^.+?\\.(\\w+\\.[a-z]+)$");
    //    return cookieSerializer;
    //}
    //
    //@Bean
    //public HttpSessionIdResolver httpSessionIdResolver() {
    //    CookieHttpSessionIdResolver cookieHttpSessionIdResolver = new CookieHttpSessionIdResolver();
    //    cookieHttpSessionIdResolver.setCookieSerializer(cookieSerializer());
    //    return cookieHttpSessionIdResolver;
    //}

}
