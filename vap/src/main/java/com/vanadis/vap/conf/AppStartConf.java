package com.vanadis.vap.conf;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.web.context.WebServerInitializedEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Configuration;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
 * @description: 启动配置类
 * @author: Created by 遥远 on 2019-01-28 11:13
 */
@Configuration
public class AppStartConf implements ApplicationListener<WebServerInitializedEvent> {

    protected final static Logger logger = LoggerFactory.getLogger(AppStartConf.class);

    @Override
    public void onApplicationEvent(WebServerInitializedEvent event) {
        try {
            logger.info("Application At http://" + InetAddress.getLocalHost().getHostAddress() + ":" + event.getWebServer().getPort());
            logger.info("Swagger     At http://" + InetAddress.getLocalHost().getHostAddress() + ":" + event.getWebServer().getPort() + "/swagger-ui.html");
        } catch (UnknownHostException e) {
            e.printStackTrace();
        }
    }
}
