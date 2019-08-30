package org.mars.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author yaojian
 * @date 2019/8/28
 */
@EnableEurekaClient
@EnableDiscoveryClient
@SpringBootApplication
public class MarsUserApplication {


    public static void main(String[] args) {
        SpringApplication.run(MarsUserApplication.class, args);
    }

}
