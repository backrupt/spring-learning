package com.itwill.guest.controller;

import java.util.List;

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;
import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestListController implements Controller{
	private GuestService guestService;
	public GuestListController(){
		try {
			guestService=new GuestService();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	public String handleRequest(HttpServletRequest request,
								HttpServletResponse response) {
		String forwardPath ="";
		try {
			List<Guest> guestList = guestService.guestList();
			request.setAttribute("guestList", guestList);
			forwardPath = "forward:/WEB-INF/views/guest_list.jsp";
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath = "forward:/WEB-INF/views/guest_error.jsp";
		}
		
		return forwardPath;
	}
	
	
}
