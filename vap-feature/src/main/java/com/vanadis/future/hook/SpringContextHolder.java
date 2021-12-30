package com.vanadis.future.hook;

import org.springframework.context.support.ApplicationObjectSupport;
import org.springframework.stereotype.Component;

/**
 * SpringContextHolder
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/5/18 5:42 下午
 */
@Component
public class SpringContextHolder extends ApplicationObjectSupport {

    public StatusHook getTask(String beanName) {
        return super.getApplicationContext().getBean(beanName, StatusHook.class);
    }
}
