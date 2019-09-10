package org.mars.user.config;

import org.mars.user.util.UidGenerator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author yaojian
 * @date 2019/9/10
 */
@Configuration
public class BeanConfig {


    @Bean
    public UidGenerator idGenerator(){
        return new UidGenerator(1);
    }

}
