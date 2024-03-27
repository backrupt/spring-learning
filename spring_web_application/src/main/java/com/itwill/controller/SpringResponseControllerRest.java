package com.itwill.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.itwill.dto.Guest;

@Controller
public class SpringResponseControllerRest {
	/******************* response text ***********************/
	@GetMapping(value ="/response_string",produces = "text/plain;charset=UTF-8")
	@ResponseBody //리턴값을 응답 바디에 넣어라!
	public String response_string() {
		/*
		 * 뷰 리졸버, 뷰 다 제끼고 메세지 컨버터
		 */
		return "hello string for javascript ajax request[한글]";
	}
	@GetMapping(value ="/response_html",produces = "text/html;charset=UTF-8")
	@ResponseBody //리턴값을 응답 바디에 넣어라!
	public String response_html() {
		/*
		 * 뷰 리졸버, 뷰 다 제끼고 메세지 컨버터
		 */
		return "<h3>hello string for javascript ajax request[한글]</h3><hr>";
	}
	@GetMapping(value ="/response_json",produces = "application/json;charset=UTF-8") //produces = "application/json;charset=UTF-8" 생략가능 디폴트가 제이슨이여서
	public @ResponseBody Guest response_json() { //이렇게 반환 형 앞에 붙여도 됨
		return Guest.builder()
				.guest_no(1)
				.guest_name("CHOI")
				.guest_email("일산")
				.guest_title("title")
				.guest_content("부리부리")
				.guest_date("2024-03-26")
				.guest_homepage("홈페이지")
				.build();
	}
	@GetMapping(value ="/response_json2",produces = "application/json;charset=UTF-8") //produces = "application/json;charset=UTF-8" 생략가능 디폴트가 제이슨이여서
	public @ResponseBody List<Guest> response_json2() { //이렇게 반환 형 앞에 붙여도 됨
		Guest g1 = Guest.builder()
				.guest_no(1)
				.guest_name("CHOI")
				.guest_email("일산")
				.guest_title("title")
				.guest_content("부리부리")
				.guest_date("2024-03-26")
				.guest_homepage("홈페이지")
				.build();
		Guest g2 = Guest.builder()
				.guest_no(1)	
				.guest_name("CHOI")
				.guest_email("일산")
				.guest_title("title")
				.guest_content("부리부리")
				.guest_date("2024-03-26")
				.guest_homepage("홈페이지")
				.build();
		List<Guest> guestList = new ArrayList<>();
		guestList.add(g1);
		guestList.add(g2);
		return guestList;
	}
	
	@GetMapping(value ="/response_xml",produces = "text/xml;charset=UTF-8") //produces = "application/json;charset=UTF-8" 생략가능 디폴트가 제이슨이여서
	public @ResponseBody Guest response_xml() { //이렇게 반환 형 앞에 붙여도 됨
		return Guest.builder()
				.guest_no(1)
				.guest_name("CHOI")
				.guest_email("일산")
				.guest_title("title")
				.guest_content("부리부리")
				.guest_date("2024-03-26")
				.guest_homepage("홈페이지")
				.build();
	}
}
