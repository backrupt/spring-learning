package com.itwill.guest.dao.mybatis;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan(basePackages = {"com.itwill.guest.dao.mybatis"})
@MapperScan(basePackages = {"com.itwill.guest.dao.mybatis.mapper"})
public class SpringBootGuestDaoImplMyBatisMain {
	public static void main(String[] args) throws Exception{
		System.out.println("----Spring Container초기화시작[ApplicationContext객체생성시작]");
		ApplicationContext applicationContext=SpringApplication.run(SpringBootGuestDaoImplMyBatisMain.class, args);
		System.out.println("----Spring Container초기화끝[ApplicationContext객체생성끝]");
		GuestDao guestDao=applicationContext.getBean(GuestDao.class);
		System.out.println(guestDao);
		System.out.println(guestDao.selectAll());
		
	}

}
