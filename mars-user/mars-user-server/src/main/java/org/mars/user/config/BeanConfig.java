package org.mars.user.config;

import org.mars.user.util.UidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author tony
 * @date 2019/9/10
 */
@Configuration
public class BeanConfig {
/*

    @Value("mars.user.worker-id")
    private String workerId;
*/


    @Bean
    public UidGenerator idGenerator(){
        return new UidGenerator(Integer.valueOf("1"));
    }

}
