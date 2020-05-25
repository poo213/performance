package com.njmetro.performance;

import com.njmetro.performance.exception.AuthorizationException;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.njmetro.performance.mapper")
public class PerformanceApplication {

    public static void main(String[] args) {
        SpringApplication.run(PerformanceApplication.class, args);
        //throw new AuthorizationException();
    }

}
