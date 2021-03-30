package com.vanadis.vap.conf.redis;

import java.util.function.Consumer;

import org.redisson.api.RLock;
import org.redisson.api.RedissonClient;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * @program: vaps
 * @description:
 * @author: 遥远
 * @create: 2019-08-15 20:58
 */
public class RedissonLock {

    @Autowired
    private RedissonClient redissonClient;

    public void lock(String lockName, Consumer consumer) {
        RLock lock = redissonClient.getLock(lockName);
        try {
            lock.lock();
            consumer.accept(lockName);
        } finally {
            lock.unlock();
        }
    }

}
