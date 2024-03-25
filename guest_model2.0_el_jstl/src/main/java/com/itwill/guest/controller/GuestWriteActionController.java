package com.itwill.guest.controller;

import com.itwill.guest.Guest;
import com.itwill.guest.GuestService;
import com.itwill.spring.mvc.Controller;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class GuestWriteActionController implements Controller {
	private GuestService guestService;
	public GuestWriteActionController() {
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
				// response.sendRedirect("guest_main.do");
				forwardPath = "redirect:guest_main.do";
			} else {
				String guest_name = request.getParameter("guest_name");
				String guest_email = request.getParameter("guest_email");
				String guest_homepage = request.getParameter("guest_homepage");
				String guest_title = request.getParameter("guest_title");
				String ip=request.getRemoteAddr();
				String guest_content = request.getParameter("guest_content");
				int rowCount = guestService.guestWrite(
						new Guest(0, guest_name, null, guest_email, guest_homepage, guest_title+"["+ip+"]", guest_content));
				// response.sendRedirect("guest_list.do");
				forwardPath = "redirect:guest_list.do";
			}
		} catch (Exception e) {
			e.printStackTrace();
			forwardPath = "forward:/WEB-INF/views/guest_error.jsp";
		}
		return forwardPath;
	}

}
