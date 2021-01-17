package com.vanadis.start.conf;

import com.vanadis.start.interceptor.TestInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * VapWebMvcConf
 *
 * @author yy287502@alibaba-inc.com
 * @date 2020/12/29 7:23 下午
 */
@Configuration
public class VapWebMvcConf implements WebMvcConfigurer {

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(new TestInterceptor()).addPathPatterns("/**");
    }
}
