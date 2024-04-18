package com.itwill.user.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ResourceBundleMessageSource;
import org.springframework.core.env.Environment;

//@Configuration
//@PropertySource("jdbc.properties")
public class ApplicationConfig {
	/*
	 application.properties파일의 값얻기위한객체
	 */
	@Autowired
	Environment environment;
	/***************MessageSource객체등록*******************/
	//@Bean("messageSource")
	public MessageSource messageSource() {
		ResourceBundleMessageSource resourceBundleMessageSource=
				new ResourceBundleMessageSource();
		resourceBundleMessageSource.setBasenames("messages/user");
		return resourceBundleMessageSource;
	}
	
	
	/***************DataSource객체등록******************
	@Bean
	public BasicDataSource apacheDataSource() {
		BasicDataSource dataSource=new  BasicDataSource();
		dataSource.setUrl(environment.getProperty("spring.datasource.url"));
		dataSource.setUsername(environment.getProperty("spring.datasource.username"));
		dataSource.setPassword(environment.getProperty("spring.datasource.password"));
		return dataSource;
	}
	
	@Bean
	public DataSource apacheDataSource() {
		BasicDataSource dataSource=new  BasicDataSource();
		dataSource.setDriverClassName("oracle.jdbc.driver.OracleDriver");
		dataSource.setUrl("jdbc:oracle:thin:@182.237.126.19:1521:XE");
		dataSource.setUsername("javaspring31");;
		dataSource.setPassword("javaspring31");
		return dataSource;
	}
	*/
	
	
}
