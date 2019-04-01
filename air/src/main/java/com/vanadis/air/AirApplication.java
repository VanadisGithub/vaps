package com.vanadis.air;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author yaoyuan
 */
@EnableFeignClients
@SpringBootApplication
public class AirApplication {

    public static void main(String[] args) {
        SpringApplication.run(AirApplication.class, args);
    }

}
