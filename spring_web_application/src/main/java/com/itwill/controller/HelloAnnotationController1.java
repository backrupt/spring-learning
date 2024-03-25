package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;

@Controller
public class HelloAnnotationController1 {
	public HelloAnnotationController1() {
		System.out.println("### HelloAnnotationController1() 생성자호출");
	}
	
	@GetMapping("/hello1")
	public String hello1(HttpServletRequest request) {
		System.out.println("### HelloAnnotationController1().hello1() 메소드호출");
		request.setAttribute("msg", "받아라얍");
		return "forward:/WEB-INF/views/hello1.jsp";
	}
}
