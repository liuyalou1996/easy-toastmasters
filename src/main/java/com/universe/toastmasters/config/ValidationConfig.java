package com.universe.toastmasters.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

/**
 * @author 刘亚楼
 * @date 2020/12/1
 */
@Configuration
public class ValidationConfig {

	@Bean
	public LocalValidatorFactoryBean defaultValidator(MessageSource messageSource) {
		LocalValidatorFactoryBean factoryBean = new LocalValidatorFactoryBean();
		factoryBean.setValidationMessageSource(messageSource);
		return factoryBean;
	}
}
