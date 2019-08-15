package com.vanadis.vap.conf.redis;

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

}
