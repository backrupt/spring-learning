package com.itwill.sns;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.PropertySource;

@SpringBootApplication
@PropertySource(value = "classpath:sns.properties")
public class SpringBootSnsLoginApplication {

	public static void main(String[] args) {
		SpringApplication.run(SpringBootSnsLoginApplication.class, args);
	}

}
