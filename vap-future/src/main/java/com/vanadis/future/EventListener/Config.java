package com.vanadis.future.EventListener;

import org.springframework.context.ApplicationEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class Config {

    @EventListener(classes = {ApplicationEvent.class})
    public void listen(ApplicationEvent event) {
        //System.out.println("事件触发：" + event.getClass().getName());
    }
}