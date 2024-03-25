package com.itwill.guest.controller;

import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestMainController implements Controller {
	
	@Override
	public String handleRequest(HttpServletRequest request,
								HttpServletResponse response) {
		String forwardPath =
				"forward:/WEB-INF/views/guest_main.jsp";
		return forwardPath;
	}
	
	
}
