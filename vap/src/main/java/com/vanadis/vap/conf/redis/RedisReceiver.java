package com.vanadis.vap.conf.redis;

import com.google.common.base.Strings;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Created by 遥远 on 2019-03-23 00:13
 */
@Slf4j
public class RedisReceiver {

    private CountDownLatch latch;

    @Autowired
    public RedisReceiver(CountDownLatch latch) {
        this.latch = latch;
    }

    public void receiveMessage(String message) {
        try {
            Thread.sleep(4000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        log.info(Strings.lenientFormat("接收到消息%s", message));
        latch.countDown();
    }
}
