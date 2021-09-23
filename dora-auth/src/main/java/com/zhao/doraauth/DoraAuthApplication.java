package com.zhao.doraauth;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.zhao.doraauth", "com.zhao.commonservice"})
public class DoraAuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoraAuthApplication.class, args);
    }

}
