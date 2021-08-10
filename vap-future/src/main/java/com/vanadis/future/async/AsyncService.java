package com.vanadis.future.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

/**
 * AsyncService
 *
 * @author yy287502@alibaba-inc.com
 * @date 2021/6/7 4:02 下午
 */
@Service
public class AsyncService {

    @Async
    public void testAsync() throws InterruptedException {
        Thread.sleep(3000);
        System.out.println("1");
    }

}
