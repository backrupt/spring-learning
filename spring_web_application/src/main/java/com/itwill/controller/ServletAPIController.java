package com.itwill.controller;

import java.util.Locale;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;

@Controller
public class ServletAPIController {
	
	@GetMapping("controller_servletapi")
	public String controller_servletapi(
			HttpServletRequest request,
			HttpServletResponse response,
			HttpSession session,
			Locale requestLocale,
			@CookieValue("JSESSIONID") String JSESSIONID
			) {
		
		System.out.println(request);
		System.out.println(response);
		System.out.println(session);
		System.out.println(requestLocale);
		System.out.println(JSESSIONID);
		return "forward:/WEB-INF/views/controller_servletapi.jsp";
	}
}
