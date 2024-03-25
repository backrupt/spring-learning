package com.itwill.datasource;

import java.sql.Connection;

import javax.sql.DataSource;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class SpringBootDataSourceApplication {
	public static void main(String[] args) throws Exception{
		System.out.println("----Spring Container초기화시작[ApplicationContext객체생성시작]");
		ApplicationContext applicationContext=
				SpringApplication.run(SpringBootDataSourceApplication.class, args);
		System.out.println("----Spring Container초기화끝[ApplicationContext객체생성끝]");
		DataSource dataSource = applicationContext.getBean(DataSource.class);
		Connection connection=dataSource.getConnection();
		System.out.println("1.DataSource:"+dataSource);
		System.out.println("2.Connection:"+connection);
	}

}
