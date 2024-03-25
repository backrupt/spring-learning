package com.itwill;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class GuestApplication {

	public static void main(String[] args) {
		SpringApplication.run(GuestApplication.class, args);
	}

}
