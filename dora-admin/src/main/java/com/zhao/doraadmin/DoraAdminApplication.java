package com.zhao.doraadmin;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;

@EnableDiscoveryClient
@SpringBootApplication(scanBasePackages = {"com.zhao"})
public class DoraAdminApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoraAdminApplication.class, args);
    }

}
