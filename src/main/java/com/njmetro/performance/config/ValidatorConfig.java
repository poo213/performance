package com.njmetro.performance.config;

import org.hibernate.validator.HibernateValidator;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.validation.Validation;
import javax.validation.Validator;

/**
 * Hibernate Validator 配置
 *
 * @author RCNJTECH
 * @date 2020/4/17 16:30
 */
@Configuration
public class ValidatorConfig {

    /**
     * 快速失败模式
     *
     * @return Validator
     */
    @Bean
    public Validator validator() {
        return Validation
                .byProvider(HibernateValidator.class)
                .configure()
                .failFast(true)
                .buildValidatorFactory()
                .getValidator();
    }

}
