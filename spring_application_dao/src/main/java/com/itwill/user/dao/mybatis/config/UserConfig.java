package com.itwill.user.dao.mybatis.config;

import javax.sql.DataSource;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;


import com.itwill.user.dao.mybatis.User;
import com.itwill.user.dao.mybatis.UserDao;
import com.itwill.user.dao.mybatis.UserDaoImplMyBatis;



@Configuration
public class UserConfig {
	
	/*@Bean
	public UserDao userDao() {
		return (UserDao)new UserDaoImplMyBatis();
	}*/
	
	@Bean
	public User user() {
		return new User("test", "1111", "테스트", "이메일");
	}
	
	@Bean
	@ConfigurationProperties(prefix = "spring.datasource")
	public DataSource dataSource() {
		return DataSourceBuilder
			.create()
			.type(DriverManagerDataSource.class)
			.build();
	}
	
}
