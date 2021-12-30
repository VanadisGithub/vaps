package com.vanadis.start.conf;

import java.net.InetAddress;
import java.net.UnknownHostException;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

/**
 * @description: 启动配置类
 * @author: Created by 遥远 on 2019-01-28 11:13
 */
@Slf4j
@Configuration
public class VapAppStartConf implements ApplicationListener<WebServerInitializedEvent> {

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            String webUrl = InetAddress.getLocalHost().getHostAddress() + ":" + event.getWebServer().getPort();
            log.info("Application At http://" + webUrl);
            log.info("Swagger     At http://" + webUrl + "/swagger-ui/index.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
    
}
