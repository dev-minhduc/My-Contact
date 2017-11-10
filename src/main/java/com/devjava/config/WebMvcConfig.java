package com.devjava.config;

import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

//Class cấu hình
@Configuration
public class WebMvcConfig extends WebMvcConfigurerAdapter{
	
	@Bean
    public MessageSource messageSource() {
        ReloadableResourceBundleMessageSource bean = new ReloadableResourceBundleMessageSource();
        bean.addBasenames("classpath:messages"); //Chỉ ra đường dẫn tới messages.properties
        return bean;
    }
}
