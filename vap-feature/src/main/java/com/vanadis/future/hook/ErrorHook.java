package com.vanadis.future.hook;

import org.springframework.stereotype.Component;

/**
 * SuccessHook
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/4/30 10:57 上午
 */
@Component
public class ErrorHook implements StatusHook {

    @Override
    public void print(String message) {
        System.out.println(this.getClass().getName() + " " + message);
    }
}
