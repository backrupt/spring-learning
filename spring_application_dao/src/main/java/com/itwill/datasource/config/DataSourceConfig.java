package com.itwill.datasource.config;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSource;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.zaxxer.hikari.HikariDataSource;

/*
@Configuration
 - 스프링부트가 초기화하면서 설정파일의 모든메쏘드를 호출한반환객체를 스프링빈으로등록한다. 
 
 */
@Configuration
public class DataSourceConfig {
	/*
	@Bean
	  -메쏘드호출후에 반환되는 객체를 빈으로등록한다.
	 */
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource.hikari")
	public DataSource dataSource() {
		System.out.println("### DataSourceConfig.dataSource()");
		
		return DataSourceBuilder
			.create()
			.type(HikariDataSource.class)
			.build();
	}
	
	
	
}
