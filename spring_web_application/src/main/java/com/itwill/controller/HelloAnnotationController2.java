package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloAnnotationController2 {
	public HelloAnnotationController2() {
		System.out.println("### HelloAnnotationController2() 생성자호출");
	}
	
	@GetMapping("/hello2")
	public String hello2(HttpServletRequest request) {
		System.out.println("### HelloAnnotationController2().hello2() 메소드호출");
		request.setAttribute("msg", "이것도 너프해 보시쥐");
		return "forward:/WEB-INF/views/hello2.jsp";
	}
}
