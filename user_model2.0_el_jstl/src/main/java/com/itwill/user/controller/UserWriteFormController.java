package com.itwill.user.controller;

import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserWriteFormController implements Controller{

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		// TODO Auto-generated method stub
		return "forward:/WEB-INF/views/user_write_form.jsp";
	}

}
