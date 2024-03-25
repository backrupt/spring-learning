package com.itwill.guest.controller;

import com.itwill.guest.GuestService;
import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestRemoveActionController implements Controller {
	private GuestService guestService;
	public GuestRemoveActionController() {
		try {
			guestService=new GuestService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
		
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String forwardPath="";
		try {
			if (request.getMethod().equalsIgnoreCase("GET")) {
				forwardPath = "redirect:guest_main.do";
			} else {
				String guest_noStr = request.getParameter("guest_no");
				int rowCount = guestService.guestDelete(Integer.parseInt(guest_noStr));
				forwardPath = "redirect:guest_list.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath = "forward:/WEB-INF/views/guest_error.jsp";
		}
		return forwardPath;
	}

}
