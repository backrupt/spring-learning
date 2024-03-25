package com.itwill.guest.controller;

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;
import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestViewController implements Controller {
	
	public String handleRequest(HttpServletRequest request,
								HttpServletResponse response) {
		String forwardPath ="";
		try {
			String guest_noStr = request.getParameter("guest_no");
			if (guest_noStr == null || guest_noStr.equals("")) {
				forwardPath = "redirect:guest_main.do";
			} else {
				GuestService guestService = new GuestService();
				Guest guest = guestService.guestDetail(Integer.parseInt(guest_noStr));
				request.setAttribute("guest", guest);
				forwardPath = "forward:/WEB-INF/views/guest_view.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath = "forward:/WEB-INF/views/guest_error.jsp";
		}
				
		return forwardPath;
	}
	
	
}
