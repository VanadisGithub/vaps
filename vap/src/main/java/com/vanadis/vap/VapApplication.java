package com.vanadis.vap;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;
import org.springframework.transaction.annotation.EnableTransactionManagement;

/**
 * @description:
 * @author: Created by 遥远 on 2019-01-26 15:21
 */
//@EnableEurekaClient
@EnableScheduling
@EnableTransactionManagement
@MapperScan("com.vanadis.proxy.mapper")
@EnableGlobalMethodSecurity(prePostEnabled=true)
@SpringBootApplication
public class VapApplication {

    public static void main(String[] args) {
        SpringApplication app = new SpringApplication(VapApplication.class);
        SpringApplication.run(VapApplication.class, args);
    }

}
