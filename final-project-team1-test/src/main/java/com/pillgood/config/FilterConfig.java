package com.pillgood.config;

import java.util.Collections;

import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.pillgood.filter.AuthFilter;

@Configuration
public class FilterConfig {
    @Bean
    public FilterRegistrationBean<AuthFilter> myFilter() {
        FilterRegistrationBean<AuthFilter> registrationBean = new FilterRegistrationBean<>();
        registrationBean.setFilter(new AuthFilter());
        // 필터가 적용될 url 패턴
        
        registrationBean.setUrlPatterns(Collections.singletonList("/api/*"));
        // 필터를 제외하고자 하는 url 패턴
        registrationBean.addUrlPatterns("/api/user/login/*");
        registrationBean.setOrder(1); // 필터 순서 설정
        return registrationBean;
    }
}