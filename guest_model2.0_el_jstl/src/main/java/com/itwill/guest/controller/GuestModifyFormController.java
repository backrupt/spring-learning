package com.itwill.guest.controller;

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;
import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestModifyFormController implements Controller {
	private GuestService guestService;
	public GuestModifyFormController() {
		try {
			guestService=new GuestService();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public String handleRequest(HttpServletRequest request, HttpServletResponse response) {
		String forwardPath = "";
		try {
			if (request.getMethod().equalsIgnoreCase("GET")) {
				forwardPath = "redirect:guest_main.do";
			} else {
				String guest_noStr = request.getParameter("guest_no");
				Guest guest = guestService.guestDetail(Integer.parseInt(guest_noStr));
				request.setAttribute("guest", guest);
				forwardPath = "forward:/WEB-INF/views/guest_modify_form.jsp";
			}
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath = "forward:/WEB-INF/views/guest_error.jsp";
		}
		return forwardPath;
	}

}
