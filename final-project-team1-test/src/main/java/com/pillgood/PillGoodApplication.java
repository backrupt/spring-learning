package com.pillgood;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;

@SpringBootApplication
@ServletComponentScan
public class PillGoodApplication {

    public static void main(String[] args) {
        SpringApplication.run(PillGoodApplication.class, args);
    }

}
