package com.vanadis.vap.conf.redis;

import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.connection.RedisConnectionFactory;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.listener.PatternTopic;
import org.springframework.data.redis.listener.RedisMessageListenerContainer;
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter;

import java.util.concurrent.CountDownLatch;

/**
 * @description:
 * @author: Created by 遥远 on 2018-10-12 19:57
 */
@Configuration
//@ConditionalOnProperty(name = "redis.enabled", havingValue = "true")
public class RedisMsgConfig {

    @Bean
    public CountDownLatch latch() {
        return new CountDownLatch(1);
    }

    @Bean
    public RedisMsgReceiver redisReceiver(CountDownLatch latch) {
        return new RedisMsgReceiver(latch);
    }

    @Bean
    public RedisMessageListenerContainer container(RedisConnectionFactory connectionFactory, MessageListenerAdapter listenerAdapter) {
        RedisMessageListenerContainer container = new RedisMessageListenerContainer();
        container.setConnectionFactory(connectionFactory);
        container.addMessageListener(listenerAdapter, new PatternTopic("theme"));
        return container;
    }

    /**
     * 消息监听器适配器，绑定消息处理器，利用反射技术调用消息处理器的业务方法
     */
    @Bean
    public MessageListenerAdapter listenerAdapter(RedisMsgReceiver redisMsgReceiver) {
        return new MessageListenerAdapter(redisMsgReceiver, "receiveMessage");
    }

    /**
     * redis 读取内容的template
     */
    @Bean
    public StringRedisTemplate template(RedisConnectionFactory connectionFactory) {
        return new StringRedisTemplate(connectionFactory);
    }
}
