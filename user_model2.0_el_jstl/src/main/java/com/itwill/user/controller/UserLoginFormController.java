package com.itwill.user.controller;

import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserLoginFormController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		return "forward:/WEB-INF/views/user_login_form.jsp";
	}

}
