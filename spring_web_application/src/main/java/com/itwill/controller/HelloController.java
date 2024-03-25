package com.itwill.controller;

import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class HelloController implements Controller{
	public HelloController() {
		System.out.println("### HelloController() 생성자");
	}
	@Override
	public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("forward:/WEB-INF/views/hello.jsp");
		request.setAttribute("msg", "안녕 SpringMVC!!!");
		return modelAndView;
	}

}
