package com.njmetro.performance.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * CORS 配置
 *
 * @author RCNJTECH
 * @date 2020/4/11 17:09
 */
@Configuration(proxyBeanMethods = false)
public class CorsConfig {

    /**
     * 允许访问所有 URI、允许所有方法
     *
     * @return WebMvcConfigurer
     */
    @Bean
    public WebMvcConfigurer webMvcConfigurer() {
        return new WebMvcConfigurer() {
            @Override
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedMethods("*");
            }
        };
    }

}
