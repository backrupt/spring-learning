package com.itwill.user.controller;

import com.itwill.spring.mvc.Controller;
import com.itwill.user.User;
import com.itwill.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

public class UserViewController implements Controller{
	private UserService userService;
	public UserViewController() throws Exception{
		userService=new UserService();
	}
	@Override
	public String handleRequest(HttpServletRequest request, 
								HttpServletResponse response) {
		/***********************로그인체크**********************/
		String sUserId = (String)request.getSession()
						.getAttribute("sUserId");
		if(sUserId==null){
			return "redirect:user_login_form.do";
		}
		/*****************************************************/
		String forwardPath="";
		try {
			User loginUser=userService.findUser(sUserId);
			request.setAttribute("loginUser", loginUser);
			forwardPath="forward:/WEB-INF/views/user_view.jsp";
			
		}catch (Exception e) {
			e.printStackTrace();
			forwardPath="forward:/WEB-INF/views/user_error.jsp";
		}
		
		return forwardPath;
	}

}
