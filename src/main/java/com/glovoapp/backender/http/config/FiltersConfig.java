package com.glovoapp.backender.http.config;

import com.glovoapp.backender.http.middleware.JWTFilter;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class FiltersConfig {

    @Bean
    public FilterRegistrationBean<JWTFilter> jwtFilter(JWTFilter jwtFilter) {
        FilterRegistrationBean<JWTFilter> registrationBean = new FilterRegistrationBean<>();

        registrationBean.setFilter(jwtFilter);
        registrationBean.addUrlPatterns("/orders/*");

        return registrationBean;
    }
}
