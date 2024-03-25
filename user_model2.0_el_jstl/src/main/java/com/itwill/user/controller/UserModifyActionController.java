package com.itwill.user.controller;

import com.itwill.spring.mvc.Controller;
import com.itwill.user.User;
import com.itwill.user.UserService;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class UserModifyActionController implements Controller{
	private UserService userService;
	public UserModifyActionController() {
		try {
			userService=new UserService();
		} catch (Exception e) {	
			e.printStackTrace();
		}
	}
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		/***********************로그인체크**********************/
		String sUserId = (String)request.getSession()
						.getAttribute("sUserId");
		if(sUserId==null){
			return "redirect:user_login_form.do";
		}
		/*****************************************************/
		String forwardPath="";
		try{
			if(request.getMethod().equalsIgnoreCase("GET")){
				response.sendRedirect("user_main.jsp");
				forwardPath="redirect:user_main.do";
				return forwardPath;
			}
			
			String password = request.getParameter("password");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			int rowCount=userService.update(new User(sUserId,password,name,email));
			forwardPath="redirect:user_view.do";
		}catch(Exception e){
			e.printStackTrace();
			forwardPath="forward:/WEB-INF/views/user_eror.jsp";
		}
		return forwardPath;
	}

}
