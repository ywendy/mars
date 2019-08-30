package org.mars.eureka;

import org.mars.eureka.listener.MarsEurekaApplicationReadyEventListener;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.server.EnableEurekaServer;

/**
 * @author yaojian
 * @date 2019/8/28
 */
@SpringBootApplication
@EnableEurekaServer
public class MarsEurekaApplication {

    public static void main(String[] args) {

        SpringApplication springApplication = new SpringApplication(MarsEurekaApplication.class);
        springApplication.addListeners(new MarsEurekaApplicationReadyEventListener());
        springApplication.run(args);

    }

}
