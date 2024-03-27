package com.itwill.user;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ApplicationContext;

@SpringBootApplication
public class UserSpringApplication {

	public static void main(String[] args)throws Exception {
		ApplicationContext applicationContext=SpringApplication.run(UserSpringApplication.class, args);
		UserDao userDao=(UserDao)applicationContext.getBean("userDaoImplMyBatis");
		System.out.println(userDao.findUserList());
	}
	
}
