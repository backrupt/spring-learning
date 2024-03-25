package com.itwill.config;

import java.util.HashMap;
import java.util.Map;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.handler.SimpleUrlHandlerMapping;

import com.itwill.controller.HelloController;

@Configuration
public class SimpleUrlHandlerMappingConfig {
	
	@Bean
	public SimpleUrlHandlerMapping simpleUrlHandlerMapping() {
		SimpleUrlHandlerMapping simpleUrlHandlerMapping = new SimpleUrlHandlerMapping();
		Map<String,Object> urlMap = new HashMap();
		urlMap.put("/hello", helloController());
		//urlMap.put("/hello", new HelloController()); (x) -> 등록되지 않음
		simpleUrlHandlerMapping.setUrlMap(urlMap);
		simpleUrlHandlerMapping.setOrder(0);
		return simpleUrlHandlerMapping;
	}
	
	@Bean
	public HelloController helloController() {
		return new HelloController();
	}
}
