package com.vanadis.vap.conf.springboot;

import org.springframework.cache.Cache;
import org.springframework.cache.annotation.CachingConfigurerSupport;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cache.interceptor.CacheErrorHandler;
import org.springframework.cache.interceptor.KeyGenerator;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 缓存配置
 * @author: Created by 遥远 on 2019-03-22 17:53
 */
@EnableCaching
@Configuration
public class SpringbootCacheConf extends CachingConfigurerSupport {

    /**
     * key生成策略
     *
     * @return
     */
    @Override
    public KeyGenerator keyGenerator() {
        return (target, method, params) -> {
            StringBuilder sb = new StringBuilder();
            sb.append(target.getClass().getName());
            sb.append("#");
            sb.append(method.getName());
            for (Object obj : params) {
                sb.append("#");
                sb.append(obj.toString());
            }
            return sb.toString();
        };
    }

    /**
     * redis缓存异常处理
     *
     * @return
     */
    @Override
    public CacheErrorHandler errorHandler() {
        return new CacheErrorHandler() {

            @Override
            public void handleCacheGetError(RuntimeException e, Cache cache, Object o) {
                throw e;
            }

            @Override
            public void handleCachePutError(RuntimeException e, Cache cache, Object o, Object o1) {
                throw e;
            }

            @Override
            public void handleCacheEvictError(RuntimeException e, Cache cache, Object o) {
                throw e;
            }

            @Override
            public void handleCacheClearError(RuntimeException e, Cache cache) {
                throw e;
            }
        };
    }
}
