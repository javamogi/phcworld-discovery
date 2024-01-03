package com.phcworlddiscovery;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

@SpringBootApplication
@EnableEurekaServer
public class PhcworldDiscoveryApplication {

    public static void main(String[] args) {
        SpringApplication.run(PhcworldDiscoveryApplication.class, args);
    }

}
