package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import jakarta.servlet.http.HttpSession;

@Controller
public class RequestMappingGETPOSTController {
	@GetMapping("/login")
	public String get_login_form() {
		return "forward:/WEB-INF/views/get_login_form.jsp";
	}
	@PostMapping("/login")
	public String post_login_action(@RequestParam(name="id") String id,@RequestParam(name="password") String password,HttpSession session) {
		System.out.println("id parameter:"+id);
		System.out.println("password parameter:"+password);
		boolean isLogin=true;
		
		if(isLogin) {
			session.setAttribute("sUserId", id);
		}
		return "forward:/WEB-INF/views/post_login_result.jsp";
	}
}
