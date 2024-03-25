package com.itwill.user.controller;

import com.itwill.spring.mvc.Controller;
import com.itwill.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserRemoveActionController implements Controller {
	private UserService userService;

	public UserRemoveActionController() {
		try {
			userService = new UserService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String forwardPath = "";
		/***********************로그인체크**********************/
		String sUserId = (String)request.getSession()
						.getAttribute("sUserId");
		if(sUserId==null){
			return "redirect:user_login_form.do";
		}
		/*****************************************************/
		try {
			if (request.getMethod().equalsIgnoreCase("GET")) {
				forwardPath = "redirect:user_main.do";
				return forwardPath;
			}
			HttpSession session=request.getSession(true);
			userService.remove(sUserId);
			session.invalidate();
			
			forwardPath="redirect:user_main.do";
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath="forward:/WEB-INF/views/user_error.jsp";
		}
		return forwardPath;
	}

}
