package com.itwill.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@Controller
public class SpringCustomTagSPELController {
	@GetMapping("/jstl_fmt_i18n")
	public String jstl_fmt_i18n() {
		return "jstl_fmt_i18n";
	}
	@GetMapping("/spring_customtag_spel")
	public String spring_customtag_spel() {
		/*
		request.setAttribute("price0", 345345435);
		request.setAttribute("name0", "kim eun hi");
		
		request.setAttribute("price1", 231151);
		request.setAttribute("name1", "LEE HYO LEE");
		request.setAttribute("married1", true);
		
		request.setAttribute("price2", 12345456);
		request.setAttribute("name2", "김태희");
		request.setAttribute("married2", true);
		
		request.setAttribute("guest", 
					new Guest(1, "김수미", new Date().toLocaleString(),
							"guard@naver.com", "http://www.google.com",
							"오늘은목요일", "Spring EL에대해 공부합니다."));
		 */
		return "spring_customtag_spel";
	}
	@GetMapping("/spring_customtag_spel_i18n")
	public String spring_customtag_spel_i18n() {
		return "spring_customtag_spel_i18n";
	}
	
	
}