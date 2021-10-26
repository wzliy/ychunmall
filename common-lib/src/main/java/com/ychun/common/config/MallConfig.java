package com.ychun.common.config;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * 整个应用的配置类
 */
@Configuration
public class MallConfig {

    @Bean
    public ModelMapper modelMapper() {
        return new ModelMapper();
    }

}
