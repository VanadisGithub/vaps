package com.vanadis.future.hook;

import org.springframework.beans.factory.BeanNameAware;
import org.springframework.stereotype.Component;

/**
 * SuccessHook
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/4/30 10:57 上午
 */
@Component
public class SuccessHook implements StatusHook, BeanNameAware {

    private String beanName;

    @Override
    public void print(String message) {
        System.out.println(this.getClass().getName() + " " + message);
    }

    @Override
    public void setBeanName(String s) {
        this.beanName = s;
    }
}
