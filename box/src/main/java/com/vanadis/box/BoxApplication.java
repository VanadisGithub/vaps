package com.vanadis.box;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:21
 */
@EnableEurekaServer
@SpringBootApplication
public class BoxApplication {

    public static void main(String[] args) {
        SpringApplication.run(BoxApplication.class, args);
    }

}
